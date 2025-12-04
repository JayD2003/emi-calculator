import { Component, signal } from '@angular/core';
import { EmiService } from '../services/emi';
import { NonNullableFormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-emi-calculator',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './emi-calculator.html',
  styleUrls: ['./emi-calculator.css']
})
export class EmiCalculator {

  response = signal<any | null>(null);
  error = signal<string | null>(null);
  emiForm;

  constructor(private fb: NonNullableFormBuilder, private emiService: EmiService) {
    this.emiForm = this.fb.group({
      principal: [null, [Validators.required, Validators.min(1)]],
      annualInterestRate: [null, [Validators.required, Validators.min(0.1), Validators.max(100)]],
      loanTermYears: [null, [Validators.required, Validators.min(0.1), Validators.max(30)]]
    });
  }

  onSubmit() {
    if (this.emiForm.invalid) return;

    this.emiService.calculateEmi(this.emiForm.getRawValue()).subscribe({
      next: res => { this.response.set(res); this.error.set(null); },
      error: err => { this.response.set(null); this.error.set(err?.error?.message || 'Something went wrong'); }
    });
  }
}
