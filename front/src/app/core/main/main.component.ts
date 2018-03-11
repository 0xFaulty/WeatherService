import { Component, OnInit } from '@angular/core';
import {WeatherService} from '../../shared/weather.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  message: string = "London";
  searchQuery: string = '';
  searching: boolean = false;

  weather: any;
  resultFound: boolean = false;

  constructor(private _weatherService: WeatherService) {
  }

  ngOnInit() {
    this._weatherService.currentMessage.subscribe(message => this.searchImages(message))
  }

  searchImages(query: string) {
    this.searching = true;
    return this._weatherService.getByCityName(query).subscribe(
      data => this.handleSuccess(data),
      error => this.handleError(error),
      () => this.searching = false
    );
  }

  handleSuccess(data) {
    this.resultFound = true;
    this.weather = data.hits;
    console.log(data.hits);
  }

  handleError(error) {
    console.log(error);
  }

}
