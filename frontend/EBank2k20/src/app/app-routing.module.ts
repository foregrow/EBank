import { KorisniciComponent } from './components/korisnici/korisnici.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { BankaComponent } from './components/banka/banka.component';
import { DelatnostComponent } from './components/delatnost/delatnost.component';
import { DelatnostDetailComponent } from './components/delatnost/delatnost-detail/delatnost-detail.component';
import { BankaDetailComponent } from './components/banka/banka-detail/banka-detail.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { KorisnikComponent } from './components/korisnik/korisnik.component';
import { DrzavaComponent } from './components/drzava/drzava.component';
import { ValutaComponent } from './components/valuta/valuta.component';
import { IzvrsilacComponent } from './components/izvrsilac/izvrsilac.component';
import { ValutaDetailComponent } from './components/valuta/valuta-detail/valuta-detail.component';
import { DrzavaDetailComponent } from './components/drzava/drzava-detail/drzava-detail.component';
import { KorisniciDetailComponent } from './components/korisnici/korisnici-detail/korisnici-detail.component';
import { KlijentiComponent } from './components/klijenti/klijenti.component';
import { UkidanjeRacunaComponent } from './components/ukidanje-racuna/ukidanje-racuna.component';
import { UkidanjeRacunaIzvrsilacComponent } from './components/ukidanje-racuna-izvrsilac/ukidanje-racuna-izvrsilac.component';
import { RacunComponent } from './components/racun/racun.component';
import { OtvaranjeRacunaComponent } from './components/racun/otvaranje-racuna/otvaranje-racuna.component';
import { OtvaranjeRacunaIzvrsilacComponent } from './components/racun/otvaranje-racuna-izvrsilac/otvaranje-racuna-izvrsilac.component';
import { KlijentiDetailComponent } from './components/klijenti/klijenti-detail/klijenti-detail.component';
import { OtvaranjeRacunaUlogovanogComponent } from './components/racun/otvaranje-racuna-ulogovanog/otvaranje-racuna-ulogovanog.component';
import { TransakcijaComponent } from './components/transakcija/transakcija.component';

const routes: Routes = [
  {path: '', redirectTo: 'korisnik', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'zahtev-otvaranja-racuna', component: OtvaranjeRacunaComponent},
  //*************************admin */
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }}, 
  {path: 'korisnici', component: KorisniciComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
  }},
  {path: 'korisnici-detail/:id', component: KorisniciDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'banke', component: BankaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'banke-detail/:id', component: BankaDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},

  {path: 'delatnosti', component: DelatnostComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'delatnosti-detail/:id', component: DelatnostDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},

  {path: 'valute', component: ValutaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'valute-detail/:id', component: ValutaDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'drzave', component: DrzavaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_ADMIN']
  }},
  {path: 'drzave-detail/:id', component: DrzavaDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_ADMIN']
    }},
  {path: 'klijenti', component: KlijentiComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_ADMIN']
  }},
  {path: 'klijenti-detail/:id', component: KlijentiDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_ADMIN']
  }},

  //************korisnik (klijent)
  {path: 'korisnik', component: KorisnikComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ROLE_KORISNIK']
    }},
  {path: 'zahtev-ukidanja-racuna', component: UkidanjeRacunaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_KORISNIK']
  }},
  {path: 'zahtev-otvaranja-racuna-klijenta', component: OtvaranjeRacunaUlogovanogComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_KORISNIK']
  }},
  {path: 'transakcija', component: TransakcijaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_KORISNIK']
  }},
  


  //***************izvrsilac
  {path: 'izvrsilac', component: IzvrsilacComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_IZVRSILAC']
  }},
  {path: 'proces-zahteva-ukidanja', component: UkidanjeRacunaIzvrsilacComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_IZVRSILAC']
  }},
  {path: 'proces-zahteva-otvaranja', component: OtvaranjeRacunaIzvrsilacComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
    roles: ['ROLE_IZVRSILAC']
  }},
  {path: 'not-found', component:NotFoundComponent},
  {path: '**', redirectTo: 'korisnik', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [
LoginComponent,AdminComponent,KorisnikComponent,IzvrsilacComponent,BankaComponent,DelatnostComponent,BankaDetailComponent,DelatnostDetailComponent,
NotFoundComponent,KorisniciComponent,KorisniciDetailComponent,DrzavaComponent,DrzavaDetailComponent,ValutaComponent,ValutaDetailComponent,KlijentiComponent,
UkidanjeRacunaComponent,UkidanjeRacunaIzvrsilacComponent,RacunComponent,OtvaranjeRacunaComponent,OtvaranjeRacunaIzvrsilacComponent,KlijentiDetailComponent,
OtvaranjeRacunaUlogovanogComponent,TransakcijaComponent]