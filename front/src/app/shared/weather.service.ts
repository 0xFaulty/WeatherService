import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class WeatherService {
  private API_KEY: string = environment.OPENWEATHER_API_KEY;
  private API_URL: string = environment.OPENWEATHER_API_URL;
  private URL_BY_CITY: string = this.API_URL + '?q=';

  private messageSource = new BehaviorSubject<string>('default message');
  currentMessage = this.messageSource.asObservable();

  constructor(private _http: HttpClient) {
  }

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  getByCityName(query = 'London') {
    console.log(this.URL_BY_CITY + 'London' + '?' + this.API_KEY);
    return this._http.get(this.URL_BY_CITY + query + '?' + this.API_KEY);
  }

}
