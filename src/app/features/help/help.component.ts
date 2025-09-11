import { Component } from '@angular/core';

interface FAQItem {
  question: string;
  answer: string;
  expanded?: boolean;
}

interface ContactInfo {
  type: string;
  value: string;
  icon: string;
}

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.scss']
})
export class HelpComponent {
  faqItems: FAQItem[] = [
    {
      question: 'How accurate are the AI predictions?',
      answer: 'Our AI system has been trained on extensive medical datasets and provides predictions with varying confidence levels. However, these predictions are meant to assist healthcare professionals and should never replace proper medical diagnosis. Always consult with a qualified healthcare provider for accurate diagnosis and treatment.',
      expanded: false
    },
    {
      question: 'What symptoms can the system analyze?',
      answer: 'The system can analyze a wide range of symptoms across multiple categories including respiratory, cardiovascular, neurological, gastrointestinal, musculoskeletal, and dermatological symptoms. You can select from our comprehensive symptom database or describe additional symptoms in the case description.',
      expanded: false
    },
    {
      question: 'How do I interpret the probability percentages?',
      answer: 'Probability percentages indicate the likelihood of each condition based on the symptoms provided. Higher percentages (80%+) suggest strong correlation with the symptoms, medium percentages (60-79%) indicate moderate correlation, and lower percentages (below 60%) suggest possible but less likely conditions.',
      expanded: false
    },
    {
      question: 'Can I save and share my prediction results?',
      answer: 'Yes, you can save your prediction results to your profile, download them as PDF reports, and share them with healthcare professionals. All saved predictions are accessible through your dashboard for future reference.',
      expanded: false
    },
    {
      question: 'Is my medical data secure and private?',
      answer: 'We take data security very seriously. All medical information is encrypted and stored securely. We comply with healthcare data protection regulations and never share your personal medical information without your explicit consent.',
      expanded: false
    },
    {
      question: 'How often should I use the prediction system?',
      answer: 'The system can be used whenever you experience new or concerning symptoms. However, it should complement, not replace, regular medical check-ups and professional healthcare consultations.',
      expanded: false
    }
  ];

  contactInfo: ContactInfo[] = [
    {
      type: 'Email Support',
      value: 'support@medprediction.com',
      icon: 'mail'
    },
    {
      type: 'Phone Support',
      value: '+1 (555) 123-4567',
      icon: 'phone'
    },
    {
      type: 'Emergency Hotline',
      value: '+1 (555) 911-HELP',
      icon: 'phone-call'
    },
    {
      type: 'Live Chat',
      value: 'Available 24/7',
      icon: 'message-circle'
    }
  ];

  toggleFAQ(index: number) {
    this.faqItems[index].expanded = !this.faqItems[index].expanded;
  }

  contactSupport(type: string) {
    switch (type) {
      case 'Email Support':
        window.location.href = 'mailto:support@medprediction.com';
        break;
      case 'Phone Support':
        window.location.href = 'tel:+15551234567';
        break;
      case 'Emergency Hotline':
        window.location.href = 'tel:+1555911HELP';
        break;
      case 'Live Chat':
        alert('Live chat feature would be implemented here');
        break;
    }
  }
}
