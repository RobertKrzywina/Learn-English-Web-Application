import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FormGroup} from "@angular/forms";
import {SubmitChallengeDto} from "./submit-challenge-dto.model";
import {Observable} from "rxjs";
import {SubmitedChallengeQuery} from "./submited-challenge-query.model";
import {PendingChallengeQuery} from "./pending-challenge-query.model";
import {SubmitPendingChallengeDto} from "./submit-pending-challenge-dto.model";
import {CompletedChallengeQuery} from "./completed-challenge-query.model";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  readonly makeUrl = '/api/challenge/make';
  readonly submitUrl = '/api/challenge/submit';
  readonly submitPendingChallengeUrl = '/api/challenge/submit-pending';
  readonly getRandomRivalUrl = '/api/user-query/random-rival';
  readonly getPendingChallengesUrl = '/api/challenge-query/defender-pending';
  readonly declinePendingChallengeUrl = '/api/challenge/';
  readonly getSubmitedChallengesUrl = '/api/challenge-query/attacker-pending';
  readonly getPendingChallengeByIdUrl = '/api/challenge-query/defender-pending';
  readonly getCompletedChallengesUrl = '/api/challenge-query/completed';
  readonly getCompleteChallengeDetailsByIdUrl = '/api/challenge-query/completed';
  readonly getCompletedChallengeDetailsCorrectAnswersByIdUrl = '/api/challenge-query/completed/correct-answers';

  constructor(private http: HttpClient) {
  }

  make(data: FormGroup): Observable<any> {
    return this.http.post(this.makeUrl, data);
  }

  submit(data: SubmitChallengeDto) {
    return this.http.post(this.submitUrl, data);
  }

  submitPendingChallenge(data: SubmitPendingChallengeDto) {
    return this.http.post(this.submitPendingChallengeUrl, data);
  }

  getRandomRival(): Observable<string> {
    return this.http.get<string>(this.getRandomRivalUrl);
  }

  getPendingChallenges(): Observable<Array<PendingChallengeQuery>> {
    return this.http.get<Array<PendingChallengeQuery>>(this.getPendingChallengesUrl);
  }

  declinePendingChallenge(challengeId: string) {
    return this.http.delete(this.declinePendingChallengeUrl + `/${challengeId}`);
  }

  getSubmitedChallenges(): Observable<Array<SubmitedChallengeQuery>> {
    return this.http.get<Array<SubmitedChallengeQuery>>(this.getSubmitedChallengesUrl);
  }

  getPendingChallengeById(challengeId: string): Observable<any> {
    return this.http.get(this.getPendingChallengeByIdUrl + `/${challengeId}`);
  }

  getCompletedChallenges(): Observable<Array<CompletedChallengeQuery>> {
    return this.http.get<Array<CompletedChallengeQuery>>(this.getCompletedChallengesUrl);
  }

  getCompletedChallengeDetailsById(challengeId: string) {
    return this.http.get(this.getCompleteChallengeDetailsByIdUrl + `/${challengeId}`);
  }

  getCompletedChallengeDetailsCorrectAnswersById(challengeId: string) {
    return this.http.get(this.getCompletedChallengeDetailsCorrectAnswersByIdUrl + `/${challengeId}`);
  }
}
