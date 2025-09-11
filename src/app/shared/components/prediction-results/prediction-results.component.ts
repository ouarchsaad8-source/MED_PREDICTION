import { Component, Input, Output, EventEmitter } from '@angular/core';

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
  selector: 'app-prediction-results',
  templateUrl: './prediction-results.component.html',
  styleUrls: ['./prediction-results.component.scss']
})
export class PredictionResultsComponent {
  @Input() results!: PredictionResult;
  @Output() newPrediction = new EventEmitter<void>();

  expandedDiseases: Set<number> = new Set();

  toggleDiseaseExpansion(index: number) {
    if (this.expandedDiseases.has(index)) {
      this.expandedDiseases.delete(index);
    } else {
      this.expandedDiseases.add(index);
    }
  }

  isDiseaseExpanded(index: number): boolean {
    return this.expandedDiseases.has(index);
  }

  getProbabilityColor(probability: number): string {
    if (probability >= 80) return 'high';
    if (probability >= 60) return 'medium';
    return 'low';
  }

  onSaveReport() {
    // Simulate saving report
    alert('Report saved successfully!');
  }

  onDownloadPDF() {
    // Simulate PDF download
    alert('PDF download started!');
  }

  onViewFullDetails() {
    // Navigate to detailed view
    alert('Navigating to full details...');
  }

  onNewPrediction() {
    this.newPrediction.emit();
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
}
