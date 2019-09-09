import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WeaterServiceService {
  
  API_KEY = 'YOUR_API_KEY';

  constructor(private httpClient: HttpClient) { }

  public getWeatherSummary(cityName: String){
    return this.httpClient.get(`http://localhost:8080/demo/v1/Weather/${cityName}`);
  }
}
