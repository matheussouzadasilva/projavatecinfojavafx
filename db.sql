CREATE TABLE usuario(
    idusuario INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    PRIMARY KEY(idusuario));

insert into usuario(login,senha,tipo)
values('usuario', '8d969eef6ecad3c29a3a629280e686cfc3f5d5a86aff3ca122c923adc6c92', 'Administrador');

--user = usuario
--password = 123456

CREATE TABLE multa (
  idmulta INTEGER NOT NULL AUTO_INCREMENT,
  infracao VARCHAR(25) NOT NULL,
  pontos INT NOT NULL,
  penalidade VARCHAR(25) NOT NULL,
  valor DOUBLE NOT NULL,
  PRIMARY KEY(idmulta)
);

CREATE TABLE automovel (
  idautomovel INT NOT NULL AUTO_INCREMENT,
  idmulta INTEGER NOT NULL,
  placa CHAR(7) NOT NULL,
  ano INT NOT NULL,
  modelo VARCHAR(25) NOT NULL,
   marca  VARCHAR(20) NOT NULL,
  PRIMARY KEY(idautomovel),
  FOREIGN KEY(idmulta)REFERENCES multa(idmulta)
);

CREATE TABLE condutor (
  idcondutor INT NOT NULL AUTO_INCREMENT,
  idautomovel INT NOT NULL,
  sexo CHAR(9) NOT NULL,
  nome VARCHAR(25) NOT NULL,
  sobrenome VARCHAR(25) NOT NULL,
  rg INT NOT NULL,
  cpf INT(11) NOT NULL,
  datanascimento Date NOT NULL,
  cnh INT NOT NULL,
  PRIMARY KEY(idcondutor),
  FOREIGN KEY(idautomovel)REFERENCES automovel(idautomovel)
);

CREATE TABLE endereco (
  idendereco INTEGER NOT NULL AUTO_INCREMENT,
  idcondutor INT NOT NULL,
  endereco VARCHAR(25) NOT NULL,
  logradouro VARCHAR(25) NOT NULL,
  numero INT NOT NULL,
  complemento VARCHAR(20) NOT NULL,
  bairro VARCHAR(25) NOT NULL,
  estado VARCHAR(25) NOT NULL,
  cidade VARCHAR(25) NOT NULL,
  cep INT(7) NOT NULL,
  PRIMARY KEY(idendereco),
  FOREIGN KEY(idcondutor)REFERENCES condutor(idcondutor)
);
