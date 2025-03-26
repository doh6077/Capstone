**This documentation is for tracking API data format**

## Sensor Metadata Format:

-   topic: smartwaste/sensor/metadata

```json
{
    "macAddress": "34:b7:da:5d:80:0c",
    "trigerPin1": 7,
    "echoPin1": 8,
    "trigerPin2": 12,
    "echoPin2": 4,
    "trigerPin3": 2,
    "echoPin3": 13
}
```

## Sensor Reading Data Format:

-   topic: smartwaste/sensor/readingdata

```json
[
    { "macAddress": "34:b7:da:5d:80:0c", "trigerPin1": 7, "echoPin1": 8, "distance": 76.704002380371094 },
    { "macAddress": "34:b7:da:5d:80:0c", "trigerPin1": 12, "echoPin1": 13, "distance": 93.5 },
    { "macAddress": "34:b7:da:5d:80:0c", "trigerPin1": 2, "echoPin1": 4, "distance": 49.18099 }
]
```
