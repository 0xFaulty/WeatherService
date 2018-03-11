import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class WeatherService {
  private API_VERSION_URL: string = environment.API_VERSION_URL;
  private API_URL: string = environment.API_URL;

  private VERSION: string = 'v1';

  private messageSource = new BehaviorSubject<string>('default message');
  currentMessage = this.messageSource.asObservable();

  constructor(private _http: HttpClient) {
  }

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  getByCityName(query = 'London') {
    console.log(this.API_URL + this.VERSION + '/city/' + 'London');
    return this._http.get(this.API_URL + this.VERSION + '/city/' + 'London');
  }

}
