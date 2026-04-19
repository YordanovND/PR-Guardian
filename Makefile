.PHONY: up down build rebuild logs clean restart

up:
	docker compose up -d

build:
	docker compose up --build -d

rebuild:
	docker compose down
	docker compose up --build -d

down:
	docker compose down

logs:
	docker compose logs -f

logs-app:
	docker compose logs -f app

clean:
	docker compose down -v

restart:
	docker compose restart