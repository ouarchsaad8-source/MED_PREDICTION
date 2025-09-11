import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

interface PredictionDetail {
  id: number;
  timestamp: string;
  symptoms: string[];
  description: string;
  onsetDate: string;
  severity: number;
  diseases: Array<{
    name: string;
    probability: number;
    reason: string;
    medications: Array<{
      name: string;
      dose: string;
      frequency: string;
      reason: string;
    }>;
  }>;
  status: string;
  notes?: string;
}

@Component({
  selector: 'app-prediction-details',
  templateUrl: './prediction-details.component.html',
  styleUrls: ['./prediction-details.component.scss']
})
export class PredictionDetailsComponent implements OnInit {
  predictionId: string | null = null;
  prediction: PredictionDetail | null = null;
  isLoading = true;

  // Mock data for demonstration
  mockPredictions: PredictionDetail[] = [
    {
      id: 1,
      timestamp: '2024-01-15T10:30:00Z',
      symptoms: ['fever', 'cough', 'headache', 'fatigue'],
      description: 'Patient experiencing persistent cough with fever for the past 3 days. Headache started yesterday morning and fatigue has been ongoing.',
      onsetDate: '2024-01-12',
      severity: 7,
      diseases: [
        {
          name: 'Upper Respiratory Infection',
          probability: 85,
          reason: 'Common symptoms match typical viral upper respiratory infection pattern',
          medications: [
            {
              name: 'Acetaminophen',
              dose: '500mg',
              frequency: 'Every 6 hours',
              reason: 'For fever and pain relief'
            },
            {
              name: 'Dextromethorphan',
              dose: '15mg',
              frequency: 'Every 4 hours',
              reason: 'For cough suppression'
            }
          ]
        },
        {
          name: 'Seasonal Allergies',
          probability: 45,
          reason: 'Symptoms could indicate allergic reaction',
          medications: [
            {
              name: 'Loratadine',
              dose: '10mg',
              frequency: 'Once daily',
              reason: 'Antihistamine for allergy symptoms'
            }
          ]
        }
      ],
      status: 'completed',
      notes: 'Patient advised to rest and increase fluid intake. Follow-up recommended if symptoms persist beyond 7 days.'
    }
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.predictionId = this.route.snapshot.paramMap.get('id');
    this.loadPredictionDetails();
  }

  loadPredictionDetails() {
    // Simulate API call
    setTimeout(() => {
      if (this.predictionId) {
        const id = parseInt(this.predictionId);
        this.prediction = this.mockPredictions.find(p => p.id === id) || null;
      }
      this.isLoading = false;
    }, 1000);
  }

  goBack() {
    this.router.navigate(['/dashboard']);
  }

  editPrediction() {
    // Navigate to edit mode or show edit modal
    alert('Edit functionality would be implemented here');
  }

  deletePrediction() {
    if (confirm('Are you sure you want to delete this prediction?')) {
      // Simulate deletion
      alert('Prediction deleted successfully');
      this.goBack();
    }
  }

  downloadReport() {
    alert('Downloading detailed report...');
  }

  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  getProbabilityColor(probability: number): string {
    if (probability >= 80) return 'high';
    if (probability >= 60) return 'medium';
    return 'low';
  }
}
