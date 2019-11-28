-- noinspection SqlNoDataSourceInspectionForFile

CREATE KEYSPACE IF NOT EXISTS cjm WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

USE cjm;

CREATE TABLE IF NOT EXISTS alunos (
id uuid,
email text,
senha text,
nome text,
endereco text,
bairro text,
cidade text,
estado text,
telefone text,
pontos int,
questoes int,
acertos int,
erros int,
PRIMARY KEY (id));

CREATE INDEX IF NOT EXISTS alunos_index_email ON alunos(email);
CREATE INDEX IF NOT EXISTS alunos_index_nome ON alunos(nome);

CREATE TABLE IF NOT EXISTS operadores (
id uuid,
email text,
senha text,
nome text,
telefone text,
perfil text,
PRIMARY KEY (id));

CREATE INDEX operadores_index_email ON operadores(email);
CREATE INDEX operadores_index_nome ON operadores(nome);
CREATE INDEX operadores_index_perfil ON operadores(perfil);

CREATE TABLE IF NOT EXISTS exercicios (
id uuid,
enunciado1 text,
imagem_enunciado blob,
enunciado2 text,
opcoes list<text>,
gabarito_objetivo int,
gabarito_subjetivo text,
pontuacao int,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS exercicios_busca (
id uuid,
enunciado1 text,
enunciado2 text,
pontuacao int,
materia list<varchar>,
banca list<varchar>,
prova list<varchar>,
ano list<int>,
PRIMARY KEY (id));

CREATE INDEX exercicios_busca_index_materia_banca_prova_ano ON exercicios_busca(materia, banca, prova, ano);

CREATE TABLE IF NOT EXISTS classificacao_padrao (
    id text,
    classificacao blob,
    PRIMARY KEY(id)
);

INSERT INTO classificacao_padrao (id, classificacao) VALUES ('1', textAsBlob('""'));

CREATE TABLE IF NOT EXISTS bancas_padrao (
    id text,
    bancas list<text>,
    PRIMARY KEY(id)
);

INSERT INTO bancas_padrao (id, bancas) VALUES ('1', ['']);

CREATE TABLE IF NOT EXISTS concursos_padrao (
 id text,
 concursos list<text>,
 PRIMARY KEY(id)
);

INSERT INTO concursos_padrao (id, concursos) VALUES ('1', ['']);

CREATE TABLE IF NOT EXISTS comentarios (
id bigint,
id_exercicio uuid,
email text,
comentario text,
PRIMARY KEY (id));

CREATE INDEX IF NOT EXISTS comentarios_index_email ON comentarios (email);
CREATE INDEX IF NOT EXISTS comentarios_index_id_exercicio ON comentarios (id_exercicio);

CREATE TABLE IF NOT EXISTS redacoes (
id uuid,
id_aluno uuid,
tema text,
pontuacao int,
redacao_original blob,
redacao_corrigida blob,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS redacoes_busca (
id uuid,
id_aluno uuid,
tema text,
pontuacao int,
PRIMARY KEY (id));

CREATE INDEX IF NOT EXISTS redacoes_busca_index_email ON redacoes_busca(id_aluno);
CREATE INDEX IF NOT EXISTS redacoes_busca_index_tema ON redacoes_busca(tema);

CREATE TABLE ids (
id_name varchar,
next_id bigint,
PRIMARY KEY (id_name)
);

INSERT INTO ids (id_name, next_id) VALUES ('comentario_id', 1);
