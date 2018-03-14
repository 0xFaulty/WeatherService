import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {SidenavComponent} from './core/sidenav/sidenav.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material.module';
import {HeaderComponent} from './core/header/header.component';
import {BackTaskService} from './shared/backtask.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MainComponent } from './core/main/main.component';
import { LoginComponent } from './core/login/login.component';
import {Error404Component} from './core/error404/error-404.component';

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    HeaderComponent,
    MainComponent,
    LoginComponent,
    Error404Component,
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'front' }),
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [BackTaskService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
