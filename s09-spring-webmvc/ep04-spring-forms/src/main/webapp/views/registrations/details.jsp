<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<app:layout title="Registrations" active="registrations">
	<h4><i class="bi-flag"></i> Registration Details</h4>
	
	<!-- Registration Info -->
	<div class="card mt-3">
		<div class="card-body">
			
			<h5 class="card-title"><i class="bi-info-circle"></i> Registration ID : ${details.id().code}</h5>
			
			<p>${details.remark()}</p>
		</div>
		
		<div class="card-footer">
			<i class="bi-clock"></i> Registered At : ${localDateTimes.format(details.registeredAt())}
		</div>
	</div>
	
	<div class="row mt-4">
		<div class="col">
			<!-- Class Info -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">
						<i class="bi-calendar"></i> Class Information
					</h5>
					<div class="mt-4">
						<app:information label="Course Name" value="${details.course()}" />
						<app:information label="Course Level" value="${details.level()}" />
						<app:information label="Start Date" value="${details.startAt()}" />
						<app:information label="Student Fees" value="${numbers.format(details.fees())} MMK" />
						<app:information label="Available Seats" value="${details.seats()} Students" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="col">
			<!-- Student Info -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">
						<i class="bi-person"></i> Student Information
					</h5>
					<div class="mt-4">
						<app:information label="Student Name" value="${details.name()}" />
						<app:information label="Phone Number" value="${details.phone()}" />
						<app:information label="Email Address" value="${details.email()}" />
						<app:information label="Last Education" value="${details.lastEducation()}" />
						<app:information label="Entry At" value="${localDateTimes.format(details.entryAt())} Students" />
					</div>
				</div>
			</div>
		</div>

	</div>
	
</app:layout>