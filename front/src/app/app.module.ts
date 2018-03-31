import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {Error404Component, HeaderComponent, HistoryComponent, LoginComponent, MainComponent, SidenavComponent} from './core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material.module';
import {WeatherService} from './shared/weather.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthenticationService} from './shared/authentication.service';
import {VersionService} from './shared/version.service';
import {AuthGuard} from './shared/auth.guard';
import {MatDialogModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    HeaderComponent,
    MainComponent,
    LoginComponent,
    Error404Component,
    HistoryComponent
  ],
  imports: [
    BrowserModule.withServerTransition({appId: 'front'}),
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule
  ],
  providers: [
    WeatherService,
    AuthenticationService,
    VersionService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
