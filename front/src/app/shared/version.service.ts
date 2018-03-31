import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {Version} from '../entity/version';

@Injectable()
export class VersionService {
  private API_VERSION_URL: string = environment.API_URL + environment.API_VERSION_PATH;
  private version: string = 'v1';

  constructor(private _http: HttpClient) {
    let currentApiVersion = JSON.parse(localStorage.getItem('currentApiVersion'));
    let storedVersion = currentApiVersion && currentApiVersion.version;
    if (storedVersion) {
      this._http.get(this.API_VERSION_URL).subscribe(data => {
        this.version = (<Version> data).version;
        localStorage.setItem('currentApiVersion', JSON.stringify({version: this.version}));
      });
    }
  }

  getCurrentVersion(): string {
    return this.version;
  }

}


