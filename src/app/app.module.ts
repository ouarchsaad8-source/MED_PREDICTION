import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Auth Components
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';

// Layout Components
import { DashboardLayoutComponent } from './layout/dashboard-layout/dashboard-layout.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';

// Feature Components
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { ProfileComponent } from './features/profile/profile.component';
import { PredictionComponent } from './features/prediction/prediction.component';
import { PredictionDetailsComponent } from './features/prediction-details/prediction-details.component';
import { HelpComponent } from './features/help/help.component';

// Shared Components
import { StatCardComponent } from './shared/components/stat-card/stat-card.component';
import { PredictionResultsComponent } from './shared/components/prediction-results/prediction-results.component';
import { ProfileModalComponent } from './shared/components/profile-modal/profile-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardLayoutComponent,
    NavbarComponent,
    SidebarComponent,
    DashboardComponent,
    ProfileComponent,
    PredictionComponent,
    PredictionDetailsComponent,
    HelpComponent,
    StatCardComponent,
    PredictionResultsComponent,
    ProfileModalComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
