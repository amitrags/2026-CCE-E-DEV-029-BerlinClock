# Berlin Clock

A Java and Spring implementation of Berlin Clock Kata using TDD.
The specifications below are used as reference
-   https://stephane-genicot.github.io/BerlinClock.html
-   https://agilekatas.co.uk/katas/BerlinClock-Kata

This solution exposes two REST API endpoints 
- `/api/berlin?time={HH:mm:ss}` this would return berlin time in 24-char format
- `/api/digital?time={24-char Berlin format}` this would return digital time in HH:mm format


## Codes Used for Berlin Clock
- **O** for Off Lamp
- **Y** For Yellow Lamp
- **R** For Red Lamp

## Tech Stack
- Java 17
- Spring Boot 4.0.1
- Jersey (JAX-RS)
- JUnit 5
- Maven

## API Endpoints Description

### Convert Digital Format to Berlin Format
```
GET /api/berlin?time={HH:mm:ss}
```

**Example:**
```
http://localhost:8080/api/berlin?time=13:17:45
# Response: ORROORRROYYROOOOOOOOYYOO
```

### Convert Berlin Format to Digital Format
```
GET /api/digital?time={24-char Berlin format}
```

**Example:**
```
http://localhost:8080/api/digital?time=ORROORRROYYROOOOOOOOYYOO
# Response: 13:17
```

## Running the Application

### Prerequisites
- Java 17+

### Run Tests
```
./mvnw test
```

### Start the Server
```
./mvnw spring-boot:run
```
The server starts at `http://localhost:8080`. 
You can then use urls in browser or postman described above to test the API.

## Project Structure

```
src/main/java/com/example/berlinclock/
    controller/
        BerlinClockController.java   # Digital -> Berlin conversion
        DigitalClockController.java  # Berlin -> Digital conversion
    service/
        TimeService.java             # Validation & conversion facade
        SecondsLampService.java
        HoursLampService.java
        MinutesLampService.java
        impl/                        # Service implementations
    dto/
        LampColor.java               # Enum: Y (Yellow), R (Red), O (Off)
```

## Examples

| Digital Time | Berlin Clock Output |
|--------------|---------------------|
| 00:00:00 | YOOOOOOOOOOOOOOOOOOOOOOO |
| 12:00:00 | YRROORROOOOOOOOOOOOOOOOO |
| 13:17:01 | ORROORRROYYROOOOOOOOOYYOO |
| 23:59:59 | ORRRRRRROYYRYYRYYRYYYYYY |


## Further improvement ideas:
- Add detailed logging 
- Add Caching if load is expected to be high
- Add security