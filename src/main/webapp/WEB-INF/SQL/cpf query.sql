USE cpf

CREATE TABLE cliente (
cpf						CHAR(11)		NOT NULL,
nome					VARCHAR(100)	NOT NULL,
email					VARCHAR(200)	NOT NULL,
limite_de_credito		DECIMAL(7,2)	NOT NULL,
dt_nascimento			DATE			NOT NULL,
PRIMARY KEY (cpf)
)

CREATE PROCEDURE sp_dig(@pos INT, @cpf CHAR(11), @dig INT OUTPUT)
AS
	DECLARE @cont		INT,
			@contCpf	INT,
			@posCpf		INT,
			@calc		INT,
			@resto		INT
	
	SET @calc = 0
	SET @contCpf = 1
	IF (@pos = 1)
	BEGIN
		SET @cont = 10
	END
	ELSE
	BEGIN
		SET @cont = 11
	END

	WHILE (@cont >= 2)
	BEGIN
		SET @posCpf = CAST(SUBSTRING(@cpf, @contCpf, 1) AS INT)
		SET @calc = @calc + @posCpf * @cont
		SET @cont = @cont - 1
		SET @contCpf = @contCpf + 1
	END

	SET @resto = @calc % 11
	IF (@resto < 2)
	BEGIN
		SET @dig = 0
	END
	ELSE
	BEGIN
		SET @dig = 11 - @resto
	END

CREATE PROCEDURE sp_valida_cpf (@cpf CHAR(11), @valido BIT OUTPUT)
AS
	DECLARE @dig1	INT,
			@dig2	INT,
			@digito	VARCHAR(2)
	
	EXEC sp_dig 1, @cpf, @dig1 OUTPUT
	EXEC sp_dig 2, @cpf, @dig2 OUTPUT
	SET @digito = CAST(@dig1 AS VARCHAR(1)) + CAST(@dig2 AS VARCHAR(1))
	IF (@digito != SUBSTRING(@cpf, 10, 2))
	BEGIN
		SET @valido = 0
	END
	ELSE
	BEGIN
		SET @valido = 1
	END

CREATE PROCEDURE sp_cpf_digitos_iguais (@cpf CHAR(11), @valido BIT OUTPUT)
AS
	DECLARE @primDig	VARCHAR(1),
			@dig		VARCHAR(1),
			@cont		INT
	
	SET @valido = 0
	SET @cont = 1
	SET @primDig = SUBSTRING(@cpf, @cont, 1)
	SET @cont = @cont + 1
	WHILE (@cont <= 11)
	BEGIN
		SET @dig = SUBSTRING(@cpf, @cont, 1)
			IF (@dig != @primDig)
		BEGIN
			SET @valido = 1
			BREAK
		END
		SET @cont = @cont + 1
	END

CREATE PROCEDURE sp_iud_cliente(@cod CHAR(1), @cpf CHAR(11), @nome VARCHAR(100), 
	@email VARCHAR(200), @limite_de_credito DECIMAL(7,2), @dt_nascimento DATE, @saida VARCHAR(30) OUTPUT)
AS
	DECLARE	@cpfValido	BIT,
			@cpfIguais	BIT
	IF (UPPER(@cod) = 'D')
	BEGIN
		DELETE cliente WHERE cpf = @cpf
		SET @saida = 'Cliente excluido com sucesso'
	END
	ELSE
	BEGIN
		EXEC sp_valida_cpf @cpf, @cpfValido OUTPUT
		EXEC sp_cpf_digitos_iguais @cpf, @cpfIguais OUTPUT
		IF (@cpfValido = 0 OR @cpfIguais = 0)
		BEGIN
			RAISERROR('Insira um CPF válido', 16, 1)
		END
		ELSE
		BEGIN
			IF (UPPER(@cod) = 'I')
			BEGIN
				INSERT INTO cliente VALUES
				(@cpf, @nome, @email, @limite_de_credito, @dt_nascimento)
				SET @saida = 'Cliente inserido com sucesso'
			END
			ELSE
			BEGIN
				IF (UPPER(@cod) = 'U')
				BEGIN
					UPDATE cliente
					SET nome = @nome, email = @email, limite_de_credito = @limite_de_credito,
						dt_nascimento = @dt_nascimento
					WHERE cpf = @cpf
					SET @saida = 'Cliente atualizado com sucesso'
				END
				ELSE
				BEGIN
					RAISERROR('Operação inválida', 16, 1)
				END
			END
		END
	END

select * from cliente