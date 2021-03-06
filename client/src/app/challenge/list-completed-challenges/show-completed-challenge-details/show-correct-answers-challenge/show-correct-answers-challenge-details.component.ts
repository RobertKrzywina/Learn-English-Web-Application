import {Component, Inject} from '@angular/core';
import {CompletedChallengeDetailsCorrectAnswersQuery} from '../../../shared/completed-challenge-details-correct-answers-query.model';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-show-correct-answers-challenge-details',
  templateUrl: './show-correct-answers-challenge-details.component.html',
  styleUrls: ['./show-correct-answers-challenge-details.component.css']
})
export class ShowCorrectAnswersChallengeDetailsComponent {

  options: string[] = ['a', 'b', 'c', 'd'];

  constructor(public dialogRef: MatDialogRef<ShowCorrectAnswersChallengeDetailsComponent>,
              @Inject(MAT_DIALOG_DATA) public answers: CompletedChallengeDetailsCorrectAnswersQuery) {
  }

  close() {
    this.dialogRef.close();
  }
}
