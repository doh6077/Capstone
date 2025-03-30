import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { SensorReadingHistory } from '../sensor-reading-history';
import { SensorHistoryService } from '../sensor-history.service';

@Component({
  selector: 'app-sensor-history',
  templateUrl: './sensor-history.component.html',
  imports: [ RouterLink],
  styleUrls: ['./sensor-history.component.css']
})
export class SensorHistoryComponent implements OnInit {
  readings: SensorReadingHistory[] = [];
  @Input() id = 0;
  constructor(
    private sensorHistoryService: SensorHistoryService,
  ) {}

  ngOnInit(): void {
    this.getReadings();
  }

  getReadings(): void {
    this.sensorHistoryService.getHistoryBySensorId(this.id).subscribe({
      next: (data) => {
        this.readings = data;

      },
      error: (err) => {
        console.error('Failed to load sensor history:', err);
      }
    });
  }
}
