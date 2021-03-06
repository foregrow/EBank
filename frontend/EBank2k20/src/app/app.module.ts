import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { KorisnikService } from './services/korisnik.service';
import { AuthGuard } from './guards/auth.guard';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { RoleGuard } from './guards/role.guard';
import { BankaService } from './services/banka.service';
import { DelatnostService } from './services/delatnost.service';
import { DrzavaService } from './services/drzava.service';
import { ValutaService } from './services/valuta.service';
import { KlijentService } from './services/klijent.service';
import { UkidanjeRacunaService } from './services/ukidanje-racuna.service';
import { RacunService } from './services/racun.service';
import { IzvestajService } from './services/izvestaj.service';
import * as FileSaver from 'file-saver';
import { DatePipe } from '@angular/common';
import { NalogService } from './services/nalog.service';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [KorisnikService,BankaService,DelatnostService,DrzavaService,ValutaService,KlijentService,UkidanjeRacunaService,RacunService,IzvestajService,NalogService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true //za multiple interceptors ako su potrebni
    },AuthGuard,RoleGuard,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

