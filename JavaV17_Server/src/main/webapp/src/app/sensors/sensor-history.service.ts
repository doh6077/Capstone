
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Sensor } from './sensor';
import { EventEmitter } from '@angular/core';

//Match the URL pattern in the @RestController
const restUrl ='/api/readings';

@Injectable({
  providedIn: 'root'
})
export class SensorHistoryService {

//Constructor needed to connect to web services.
constructor(private http:HttpClient) { }

 // Get all readings for a specific sensor
 getHistoryBySensorId(sensorId: number): Observable<any[]> {
  return this.http.get<any[]>(`${restUrl}/sensor/${sensorId}`);
}

}
