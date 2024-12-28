FROM postgres:alpine

RUN apk update && apk add --no-cache tzdata

ENV POSTGRES_DB=shopdb
ENV POSTGRES_USER=shopdb
ENV POSTGRES_PASSWORD=shopdb

EXPOSE 5432/tcp