import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export interface Symptom {
  id: number;
  name: string;
  category: string;
  description: string;
}

export interface PredictionRequest {
  symptoms: number[];
  severityLevels: { [key: number]: number };
  additionalNotes?: string;
}

export interface Disease {
  id: number;
  name: string;
  description: string;
  category: string;
}

export interface Medication {
  id: number;
  name: string;
  dosage: string;
  frequency: string;
  sideEffects: string;
}

export interface Prediction {
  id: number;
  userId: number;
  symptoms: Symptom[];
  predictedDiseases: Disease[];
  recommendedMedications: Medication[];
  confidenceScore: number;
  additionalNotes: string;
  createdAt: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class PredictionService {
  private apiUrl = 'http://localhost:8082/api';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  getSymptoms(): Observable<Symptom[]> {
    return this.http.get<Symptom[]>(`${this.apiUrl}/symptoms/public`);
  }

  createPrediction(request: PredictionRequest): Observable<Prediction> {
    return this.http.post<Prediction>(`${this.apiUrl}/predictions`, request, {
      headers: this.getHeaders()
    });
  }

  getUserPredictions(): Observable<Prediction[]> {
    return this.http.get<Prediction[]>(`${this.apiUrl}/predictions/user`, {
      headers: this.getHeaders()
    });
  }

  getPredictionById(id: number): Observable<Prediction> {
    return this.http.get<Prediction>(`${this.apiUrl}/predictions/${id}`, {
      headers: this.getHeaders()
    });
  }

  deletePrediction(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/predictions/${id}`, {
      headers: this.getHeaders()
    });
  }
}
