import { Component, CUSTOM_ELEMENTS_SCHEMA, signal } from '@angular/core';
import { EmiCalculator } from './emi-calculator/emi-calculator';

@Component({
  selector: 'app-root',
  imports: [EmiCalculator],
  templateUrl: './app.html',
  styleUrl: './app.css',
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class App {
}
