/**
  *postgres에서 uuid를 생성 위함
  *uuid_generate_v1(): 하드웨어 주소(MAC 주소)와 현재 시간을 기반
  *uuid_generate_v4(): 임의의 난수를 사용하여 UUID를 생성
  *고유성 보장, 분산 시스템에 적합, 보안에 좋음
 */
CREATE TABLE IF NOT EXISTS  "uuid-ossp";
CREATE TABLE IF NOT EXISTS boards(
    id UUID PRIMARY KEY,
    --타임존 고려
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    user_id UUID REFERENCES users(id) ON DELETE SET NULL
)

create table if not exists users(
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
)