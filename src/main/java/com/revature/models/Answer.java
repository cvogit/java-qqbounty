package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="answers")
public class Answer {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int answer_id;
		
		
		@NotNull
		@Column
		private String description;
		
		//Timestamp is generated via a utility, leave null on API call
		@Nullable
		private Timestamp submitted;

		@NotNull
		private int votes;
		
		@NotNull
		private int status_id;

		
		
		public Answer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Answer(int answer_id, @NotNull String description, Timestamp submitted, @NotNull int votes,
				@NotNull int status_id) {
			super();
			this.answer_id = answer_id;
			this.description = description;
			this.submitted = submitted;
			this.votes = votes;
			this.status_id = status_id;
		}


		public int getAnswer_id() {
			return answer_id;
		}



		public void setAnswer_id(int answer_id) {
			this.answer_id = answer_id;
		}

		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public Timestamp getSubmitted() {
			return submitted;
		}



		public void setSubmitted(Timestamp submitted) {
			this.submitted = submitted;
		}



		public int getVotes() {
			return votes;
		}



		public void setVotes(int votes) {
			this.votes = votes;
		}



		public int getStatus_id() {
			return status_id;
		}



		public void setStatus_id(int status_id) {
			this.status_id = status_id;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + answer_id;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + status_id;
			result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
			result = prime * result + votes;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Answer other = (Answer) obj;
			if (answer_id != other.answer_id)
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (status_id != other.status_id)
				return false;
			if (submitted == null) {
				if (other.submitted != null)
					return false;
			} else if (!submitted.equals(other.submitted))
				return false;
			if (votes != other.votes)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Answer [answer_id=" + answer_id + ", description=" + description + ", submitted=" + submitted
					+ ", votes=" + votes + ", status_id=" + status_id + "]";
		}


		
		
}
