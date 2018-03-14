import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class BackTaskService {
  private API_VERSION_URL: string = environment.API_URL + environment.API_VERSION_PATH;
  private API_URL: string = environment.API_URL;
  private VERSION: string = 'v1';

  private messageSource = new BehaviorSubject<string>('default message');
  currentMessage = this.messageSource.asObservable();

  constructor(private _http: HttpClient) {
    this._http.get(this.API_VERSION_URL).subscribe(data => {
      this.VERSION = (<Version> data).version;
    });
  }

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  getByCityName(query) {
    query = query.replaceAll(' ', '_');
    console.log(this.API_URL + this.VERSION + '/city/' + query + '/' + 'token');
    return this._http.get(this.API_URL + this.VERSION + '/city/' + query + '/' + 'token');
    // return interval(100).map(() => this._http.get(this.API_URL + this.VERSION + '/city/' + query));
  }

}

class Version {

  private _version: string;

  get version(): string {
    return this._version;
  }

}
