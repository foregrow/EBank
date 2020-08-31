import { KorisniciComponent } from './components/korisnici/korisnici.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { KorisnikComponent } from './components/korisnik/korisnik.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { BankaComponent } from './components/banka/banka.component';
import { DelatnostComponent } from './components/delatnost/delatnost.component';
import { DelatnostDetailComponent } from './components/delatnost/delatnost-detail/delatnost-detail.component';
import { BankaDetailComponent } from './components/banka/banka-detail/banka-detail.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  {path: '', redirectTo: 'korisnik', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ADMIN']
    }},
  {path: 'korisnik', component: KorisnikComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['KORISNIK']
    }},
    {path: 'korisnici', component: KorisniciComponent, canActivate: [AuthGuard,RoleGuard],
    data: {
        roles: ['ADMIN']
      }},

  {path: 'banke', component: BankaComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ADMIN']
    }},
  {path: 'banke-detail/:id', component: BankaDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ADMIN']
    }},

  {path: 'delatnosti', component: DelatnostComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ADMIN']
    }},
  {path: 'delatnosti-detail/:id', component: DelatnostDetailComponent, canActivate: [AuthGuard,RoleGuard],
  data: {
      roles: ['ADMIN']
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
LoginComponent,AdminComponent,KorisnikComponent,BankaComponent,DelatnostComponent,BankaDetailComponent,DelatnostDetailComponent,
NotFoundComponent,KorisniciComponent]