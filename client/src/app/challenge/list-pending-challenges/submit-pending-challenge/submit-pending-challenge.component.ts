import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {QuestionQuery} from "../../shared/question-query.model";
import {ChallengeService} from "../../shared/challenge.service";
import {SharedService} from "../../../shared/shared.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SubmitPendingChallengeDto} from "../../shared/submit-pending-challenge-dto.model";

@Component({
  selector: 'app-submit-pending-challenge',
  templateUrl: './submit-pending-challenge.component.html',
  styleUrls: ['./submit-pending-challenge.component.css']
})
export class SubmitPendingChallengeComponent implements OnInit {

  dto = new SubmitPendingChallengeDto();
  submitPendingChallengeForm: FormGroup;

  options: string[] = ['a', 'b', 'c', 'd'];

  answer1: string;
  answer2: string;
  answer3: string;
  answer4: string;
  answer5: string;

  constructor(public dialogRef: MatDialogRef<SubmitPendingChallengeComponent>,
              @Inject(MAT_DIALOG_DATA) public questions: Array<QuestionQuery>,
              private service: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.submitPendingChallengeForm = new FormGroup({
      'answer1': new FormControl(this.answer1, [Validators.required]),
      'answer2': new FormControl(this.answer2, [Validators.required]),
      'answer3': new FormControl(this.answer3, [Validators.required]),
      'answer4': new FormControl(this.answer4, [Validators.required]),
      'answer5': new FormControl(this.answer5, [Validators.required]),
    });

    this.dto.challengeId = this.questions[5];
  }

  onSubmit(submitPendingChallengeForm: FormGroup) {
    this.dto.questionsIds = [];
    this.dto.answers = [];

    for (let i = 0; i < 5; i++) {
      this.dto.questionsIds[i] = +submitPendingChallengeForm.controls[`answer${i + 1}`].value.split(":", 1);
      this.dto.answers[i] = submitPendingChallengeForm.controls[`answer${i + 1}`].value.split(":", 2)[1];
    }

    this.service.submitPendingChallenge(this.dto).subscribe(
      () => {
        this.close();
        this.sharedService.showSuccessToastr('Successfully completed challenge');
      }, () => {
        this.sharedService.showFailureToastr('Something went wrong');
      }
    );
  }

  close() {
    this.dialogRef.close();
  }
}