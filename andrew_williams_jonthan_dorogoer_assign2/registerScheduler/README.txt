CS442 Design Patterns
Fall 2016
PROJECT 2 README FILE

Due Date: Friday, September 30, 2016
Submission Date: Friday, September 30, 2016
Grace Period Used This Project: 0
Grace Period Remaining: Not sure
Author(s): Andrew Williams
	   Jonathan Dorogoer
e-mail(s): awilli64@binghamton.edu
	   jdorogo1@binghamton.edu

PURPOSE:

[
This program is designed to effeciently assign students their classes based on their specified preference. In addition this This algorithm achieves the solution with the use of multiple
threads.
]

ANT INSTRUCTIONS:

[

## To clean:
ant -buildfile src/build.xml clean

## To compile: 
ant -buildfile src/build.xml all

## To run by specifying arguments from command line [similarly for the 2nd argument and so on ...]
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=firstarg 

## To run by specifying args in build.xml (just for debugging, not for submission)
ant -buildfile src/build.xml run

## To create tarball for submission
ant -buildfile src/build.xml tarzip
]


DATA STRUCTURES:

[
In Results, we chose to store the students in an ArrayList.  This makes sense because we need to make many insertions into the ArrayList and often need to get students out of the list, which are both O(1) operations.
ArrayLists have O(n) remove operations, but since we never remove a student from our list, this was not a problem.

In ObjectPool, we chose to represent the class seats with a simple array of integers.  This allowed O(1) access and modification, which are the only operations we need to do.  The array was a good choice since we know how many classes there are and how many seats are in each class ahead of time, and these values will not change during the
course of execution.
]

TIME COMPLEXITY (run() method):

[
We first define N to be the number of students.

Our run method has a main loop which runs once for each student.  Inside that loop we have two consececutive (not nested) while loops.  The first loop will run a maximum of COURSES_PER_SUDENT times.  The second loop also runs a maximum of COURSES_PER_STUDENT times, but contains a nested loop which runs a maximum of NUM_COURSES times.

Thus our run time is given by O(N * (O(COURSES_PER_STUDENT) + O(COURSES_PER_STUDENT) * O (NUM_COURSES))
Since COURSES_PER_STUDENT and NUM_COURSES are both constants, this simplifies to a run time of O(N).

]

