import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Trashbins } from './trashbins';
import { EventEmitter } from '@angular/core';

const restUrl ='/api/trashbins';
@Injectable({
  providedIn: 'root'
})
export class TrashbinsService {

  //Constructor needed to connect to web services.
  constructor(private http:HttpClient) { }
  onTrashbinsAdded = new EventEmitter<Trashbins>();
    //Create a connection to the GET mapping in the
    //rest controller that returns a collection of trashbinss.
    getAll(): Observable<Trashbins[]>{
    return this.http.get<Trashbins[]>(restUrl);
  }
  //Create a connection to the POST mapping in the
  //rest controller. "data" will be the new trashbins.
  create(data:any):Observable<any>{
    return this.http.post(restUrl, data);
  }
}
