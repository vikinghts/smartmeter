\c smartmeterdb
DROP TABLE measurements;
\c postgres
DROP DATABASE smartmeterdb;
DROP USER smartmeteruser;

CREATE USER smartmeteruser WITH PASSWORD 'pp4pass';
ALTER USER smartmeteruser password 'pp4pass';
CREATE DATABASE smartmeterdb WITH OWNER smartmeteruser TEMPLATE template0 TABLESPACE  pg_default LC_COLLATE  'C' LC_CTYPE  'C' CONNECTION LIMIT  -1;
GRANT ALL PRIVILEGES ON DATABASE smartmeterdb to smartmeteruser;

\c smartmeterdb
CREATE TABLE measurements (datetime varchar(20),
                                        curpower varchar(20),
                                        totalnightpower varchar(20),
                                        totaldaypower varchar(20),
                                        totalgas varchar(20));
GRANT ALL PRIVILEGES ON TABLE measurements TO smartmeteruserp