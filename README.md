# demo-auto-test

## Opis
Projekt testów automatycznych korzystających z Selenide dla prostej aplikacji napisanej w Pythonie z użyciem Flask.

Dostępne moduły:
 - logowanie
 - rejestracja

## Konfiguracja
- Adres frontendu aplikacji:
app_url=http\://127.0.0.1\:5000

- Poświadczenia użytkownika zarejestrowanego w systemie:
default_email=admin@test.com
default_password=admin1234
default_username=admin1234

- Nieprawidłowy adres email:
invalid_email=mail

- Iteracja testów (automatycznie aktualizowana po testach modułu):
iteration=11

- Ustawienia bezpieczeństwa oraz poświadczeń ich niespełniających:
min_password_length=6
min_username_length=4
short_password=aDmIn
short_username=aDmIn


## Uruchomienie
mvn clean test