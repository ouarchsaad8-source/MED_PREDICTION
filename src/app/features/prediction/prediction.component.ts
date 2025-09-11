import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface Symptom {
  id: string;
  name: string;
  category: string;
}

interface PredictionResult {
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
  timestamp: string;
  symptoms: string[];
  description: string;
  onsetDate: string;
  severity: number;
}

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.scss']
})
export class PredictionComponent implements OnInit {
  predictionForm!: FormGroup;
  isLoading = false;
  showResults = false;
  predictionResults: PredictionResult | null = null;

  availableSymptoms: Symptom[] = [
    { id: 'fever', name: 'Fever', category: 'General' },
    { id: 'headache', name: 'Headache', category: 'Neurological' },
    { id: 'cough', name: 'Cough', category: 'Respiratory' },
    { id: 'shortness_breath', name: 'Shortness of breath', category: 'Respiratory' },
    { id: 'chest_pain', name: 'Chest pain', category: 'Cardiovascular' },
    { id: 'nausea', name: 'Nausea', category: 'Gastrointestinal' },
    { id: 'vomiting', name: 'Vomiting', category: 'Gastrointestinal' },
    { id: 'diarrhea', name: 'Diarrhea', category: 'Gastrointestinal' },
    { id: 'fatigue', name: 'Fatigue', category: 'General' },
    { id: 'muscle_pain', name: 'Muscle pain', category: 'Musculoskeletal' },
    { id: 'joint_pain', name: 'Joint pain', category: 'Musculoskeletal' },
    { id: 'rash', name: 'Skin rash', category: 'Dermatological' },
    { id: 'dizziness', name: 'Dizziness', category: 'Neurological' },
    { id: 'sore_throat', name: 'Sore throat', category: 'Respiratory' }
  ];

  selectedSymptoms: string[] = [];
  severityValue = 5;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.initializeForm();
  }

  initializeForm() {
    this.predictionForm = this.fb.group({
      symptoms: [[], [Validators.required]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      onsetDate: ['', [Validators.required]],
      severity: [5, [Validators.required, Validators.min(1), Validators.max(10)]]
    });
  }

  toggleSymptom(symptomId: string) {
    const index = this.selectedSymptoms.indexOf(symptomId);
    if (index > -1) {
      this.selectedSymptoms.splice(index, 1);
    } else {
      this.selectedSymptoms.push(symptomId);
    }
    this.predictionForm.patchValue({ symptoms: this.selectedSymptoms });
  }

  isSymptomSelected(symptomId: string): boolean {
    return this.selectedSymptoms.includes(symptomId);
  }

  onSeverityChange(event: any) {
    this.severityValue = event.target.value;
  }

  onSubmit() {
    if (this.predictionForm.valid) {
      this.isLoading = true;
      this.showResults = false;

      // Simulate AI prediction call
      setTimeout(() => {
        this.predictionResults = this.generateMockResults();
        this.isLoading = false;
        this.showResults = true;
      }, 2000);
    }
  }

  private generateMockResults(): PredictionResult {
    // Mock prediction results based on selected symptoms
    const mockResults: PredictionResult = {
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
          probability: 65,
          reason: 'Symptoms could indicate allergic reaction, especially with respiratory involvement',
          medications: [
            {
              name: 'Loratadine',
              dose: '10mg',
              frequency: 'Once daily',
              reason: 'Antihistamine for allergy symptoms'
            },
            {
              name: 'Fluticasone nasal spray',
              dose: '2 sprays',
              frequency: 'Twice daily',
              reason: 'For nasal congestion and inflammation'
            }
          ]
        },
        {
          name: 'Viral Gastroenteritis',
          probability: 45,
          reason: 'If gastrointestinal symptoms are present, could indicate stomach flu',
          medications: [
            {
              name: 'Oral rehydration solution',
              dose: '250ml',
              frequency: 'Every 2 hours',
              reason: 'To prevent dehydration'
            },
            {
              name: 'Probiotics',
              dose: '1 capsule',
              frequency: 'Twice daily',
              reason: 'To restore gut flora balance'
            }
          ]
        }
      ],
      timestamp: new Date().toISOString(),
      symptoms: this.selectedSymptoms,
      description: this.predictionForm.value.description,
      onsetDate: this.predictionForm.value.onsetDate,
      severity: this.predictionForm.value.severity
    };

    return mockResults;
  }

  resetForm() {
    this.predictionForm.reset();
    this.selectedSymptoms = [];
    this.severityValue = 5;
    this.showResults = false;
    this.predictionResults = null;
    this.initializeForm();
  }

  getSymptomsByCategory(category: string): Symptom[] {
    return this.availableSymptoms.filter(symptom => symptom.category === category);
  }

  getUniqueCategories(): string[] {
    return [...new Set(this.availableSymptoms.map(symptom => symptom.category))];
  }
}
