import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, ValidatorFn, AbstractControl } from '@angular/forms';
import { of } from 'rxjs';
import { WeaterServiceService } from '../../service/weater-service.service';
import { weatherInterface } from '../../model/weather.interface';
import { map, filter, catchError } from 'rxjs/operators';

@Component({
  selector: 'weatherFrom',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  headElements = ['DAY', 'ICON', 'DESCRIPTION']; form: FormGroup;
  locations = [];
  cityName: string;
  daily = [];
  weather: weatherInterface;


  constructor(private formBuilder: FormBuilder, private weatherService: WeaterServiceService) {

    this.form = this.formBuilder.group({
      locations: ['']
    });

    of(this.getLocations()).subscribe(locations => {
      this.locations = locations;
      this.form.controls.locations.patchValue(this.locations[0].id);
    });
  }

  ngOnInit() {
    this.getWeatherSummary(this.locationName.value);
  }

  getWeatherSummary(cityName: string) {
    this.daily = [];
    this.weatherService.getWeatherSummary(cityName)
      .pipe(
        filter((res: weatherInterface) => !!res),
        map((res: weatherInterface) => res),
        catchError((err) => {
          alert('We are working to solution the failure');
          console.log('ERROR', err);
          return null;
        })
      )
      .subscribe((data: weatherInterface) => {
        this.daily = data.daily.data;
        this.weather = data;
      });
  }

  get locationName(): AbstractControl {
    return this.form.get('locations');
  }

  getLocations() {
    return [
      { id: 'NEWYORK', name: 'New york' },
      { id: 'LONDON', name: 'London' },
      { id: 'MADRID', name: 'Madrid' },
      { id: 'BOGOTA', name: 'Bogota' },
      { id: 'LIVERPOOL', name: 'Liverpool' }
    ];
  }
  submit() {
    console.log(this.form.value);
  }
}