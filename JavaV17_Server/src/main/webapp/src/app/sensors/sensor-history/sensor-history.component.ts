import { Component, OnInit } from '@angular/core';
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
  sensorId!: number;
  readings: SensorReadingHistory[] = [];

  constructor(
    private sensorHistoryService: SensorHistoryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.sensorId = +this.route.snapshot.paramMap.get('id')!;
    this.getReadings();
  }

  getReadings(): void {
    this.sensorHistoryService.getHistoryBySensorId(this.sensorId).subscribe({
      next: (data) => {
        this.readings = data;
      },
      error: (err) => {
        console.error('Failed to load sensor history:', err);
      }
    });
  }
}
