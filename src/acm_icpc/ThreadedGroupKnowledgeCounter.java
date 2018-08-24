/**
 * my solution to the HR acm icpc challenge
 * note, this is a little different to the actual requirements:
 * for example, I generate knowledge of topics randomly and I don't read the number of people and topics from stdin
 */

package acm_icpc;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Teedles
 *
 */
public class GroupKnowledgeCounter {

	public final static int KNOWLEDGE_BANDWITH = 500000;
	public final static int PERSON_BANDWITH = 500;

	/**
	 * 
	 */
	public GroupKnowledgeCounter() {}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long stopTime;
		List<Group> groups = new ArrayList<Group>();

		//SETUP
		startTime = System.currentTimeMillis();
		System.out.println("Generating people...");
		List<Person> people = personGeneration();
		// List<Person> people = threadedPersonGeneration();

		int maxGroups = returnMaxGroups(PERSON_BANDWITH);

		System.out.println("Generating groups...");
		groups = generateGroups(people);


		// COMPUTATION
		System.out.println("Counting group knowledge...");
		// int[] maxKnownTopics = groupProcessor(groups);
		int[] maxKnownTopics = threadedGroupProcessor(groups);

		stopTime = System.currentTimeMillis(); 

		// PRESENTATION
		System.out.println("Max possible groups is: " + maxGroups);
		System.out.println("Max known topics is: " + maxKnownTopics[0]);
		System.out.println("Number of Groups that know the max Topics is: " + maxKnownTopics[1]);
		System.out.println("Computation took: "+ (stopTime - startTime) + " millis");

		System.exit(0);
	}

	/**
	 *  for all our groups, determine the max topics known
	 *  also count the amount of groups that know that number of topics
	 * @param groups
	 * @return an int array with the numbers counted
	 */
	private static int[] groupProcessor(List<Group> groups) {

		long start = System.currentTimeMillis();
		int[] maxKnownTopicsAndGroups = new int[] {0,0};
		int maxKnownTopics = 0; 


		maxKnownTopicsAndGroups[0] = maxKnownTopics;

		for (Group p : groups) {
			if (p.getGroupKnowledge().cardinality() == maxKnownTopics) {
				maxKnownTopicsAndGroups[1]++;
			}
		}

		for (Group g : groups) {		
			if (maxKnownTopics < g.getGroupKnowledge().cardinality()) {
				maxKnownTopics = g.getGroupKnowledge().cardinality();
			}	
		}

		maxKnownTopicsAndGroups[0] = maxKnownTopics;

		for (Group p : groups) {
			if (p.getGroupKnowledge().cardinality() == maxKnownTopics) {
				maxKnownTopicsAndGroups[1]++;
			}
		}

		long stop = System.currentTimeMillis();
		System.out.println("Counting group knowledge took " + (stop - start) + " millis.");
		return maxKnownTopicsAndGroups;
	}

	/**
	 *  for our array of people, create the groups they will be a member of
	 *  actually kind of the reverse: every other person will be grouped with the current person
	 *  but we pop the people already grouped off the list to ensure that  p1 and p2 are grouped
	 *  but that p2 will not then form a group with p1. we do this by making a list of the people already grouped up
	 * @param people
	 * @return a list of groups
	 */
	private static List<Group> generateGroups(List<Person> people) {

		long start = System.currentTimeMillis();

		List<Group> tempGroups = new ArrayList<Group>();
		List<Person> members = new ArrayList<Person>(people);

		for (Person p : people) {
			members.remove(p);

			for (Person q : members) {
				p.getPersonKnowledge().or(q.getPersonKnowledge());
				tempGroups.add(new Group((BitSet) p.getPersonKnowledge().clone()));
			}
		}	

		long stop = System.currentTimeMillis();

		System.out.println("Generating groups took " + (stop - start) + " millis.");

		return tempGroups;

	}


	private static int[] threadedGroupProcessor(List<Group> groups) {
		long start = System.currentTimeMillis();
		int[] results = new int[] {0,0};
		// check the number of available processors
		int nThreads = 1; // Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(nThreads);	
		List<Future<int[]>> resultSetProcessedGroups = new ArrayList<Future<int[]>>();

		for (int i=0; i< nThreads; i++) {
			List<Group> list = new ArrayList<Group>();

			// split the list
			if (i==0) {
				list = groups.subList(0, (groups.size() / nThreads));
			}

			list = groups.subList((((groups.size() / nThreads)*i) + 1), ((groups.size() / nThreads)*(i+1)));

			Callable<int[]> groupProcessor =  new GroupProcessor(list);
			Future<int[]> submit = es.submit(groupProcessor);
			resultSetProcessedGroups.add(submit);

		}

		for (Future<int[]> f : resultSetProcessedGroups) {
			try {
				if (f.get()[0] > results[0]) {
					results[0] = f.get()[0];
				}

				results[1] += f.get()[1];

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {}
		}

		es.shutdown();

		long stop = System.currentTimeMillis();
		System.out.println("Threaded counting group knowledge took " + (stop - start) + " millis.");
		return results;
	}


	/**
	 * make a bunch of people according to our configured bandwidth
	 * @param 
	 * @return a List of people
	 */
	public static List<Person> personGeneration() {
		long start = System.currentTimeMillis();
		Random rand = new Random();
		List<Person> people = new ArrayList<Person>();

		for (int i=0; i<PERSON_BANDWITH; i++) {
			people.add(new Person(rand));
		}

		long stop = System.currentTimeMillis();

		System.out.println("Generating people took " + (stop - start) + " millis.");

		return people;
	}

	public static List<Person> threadedPersonGeneration() {
		long start = System.currentTimeMillis();

		// check the number of available processors
		int nThreads = 1; // Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(nThreads);
		List<Future<List<Person>>> resultSet = new ArrayList<Future<List<Person>>>();				

		List<Person> people = new ArrayList<Person>();

		int numToGenerate = (PERSON_BANDWITH / nThreads);

		for (int i=0; i< nThreads; i++) {


			Callable<List<Person>> personGenerator =  new PersonGenerator(numToGenerate);
			Future<List<Person>> submit = es.submit(personGenerator);
			resultSet.add(submit);
		}

		for (Future<List<Person>> f : resultSet) {
			try {
				people.addAll(f.get());
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}

		long stop = System.currentTimeMillis();

		System.out.println("Threaded generating people took " + (stop - start) + " millis.");

		return people;
	}


	/**
	 * we compute the number of possible groups as a check. not really needed.
	 * using N*(N-1)/2 to determine max groups of 2 people 
	 * @param numPeople
	 * @return an int with maxGroups value
	 */
	public static int returnMaxGroups(int numPeople) {
		return numPeople * (numPeople - 1) / 2;	
	}

	/**
	 * person class. overkill really but hey ho
	 * note: on instantiation, the persons knowledge is generated
	 *
	 */
	public static class Person {

		private BitSet personKnowledge = new BitSet(KNOWLEDGE_BANDWITH);

		/**
		 * @return the personKnowledge
		 */
		public BitSet getPersonKnowledge() {
			return personKnowledge;
		}

		/**
		 * @param personKnowledge the personKnowledge to set
		 */
		public void setPersonKnowledge(BitSet personKnowledge) {
			this.personKnowledge = personKnowledge;
		}

		public Person(Random r) {
			for (int i=0; i<KNOWLEDGE_BANDWITH; i++) {
				if (r.nextBoolean() == true) this.personKnowledge.set(i);
			}
		}
	}

	public static class PersonGenerator implements Callable<List<Person>> {
		private int numToGenerate;
		private List<Person> resultList = new ArrayList<Person>();
		Random rand = new Random();
		
		public PersonGenerator(int num) {
			this.numToGenerate = num;
		}

		@Override
		public List<Person> call() throws Exception {

			for (int i=0; i< numToGenerate; i++) {
				resultList.add(new Person(rand));
			}
			return resultList;
		}
	}

	/**
	 * group class. also overkill really
	 * note: on instantiation, the OR of the two persons knowledge is generated
	 *
	 */
	public static class Group {

		/**
		 * @return the groupKnowledge
		 */
		public BitSet getGroupKnowledge() {
			return groupKnowledge;
		}

		/**
		 * @param groupKnowledge the groupKnowledge to set
		 */
		public void setGroupKnowledge(BitSet groupKnowledge) {
			this.groupKnowledge = groupKnowledge;
		}

		private BitSet groupKnowledge;

		public Group(BitSet knowledge) {

			this.setGroupKnowledge(knowledge);
		}
	}

	public static class GroupProcessor implements Callable<int[]> {
		private List<Group> myGroups;

		public GroupProcessor(List<Group> groups) {
			this.myGroups = groups;
		}

		@Override
		public int[] call() throws Exception {
			int[] maxKnownTopicsAndGroups = new int[] {0,0};
			int maxKnownTopics = 0; 

			for (Group g : myGroups) {		
				if (maxKnownTopics < g.getGroupKnowledge().cardinality()) {
					maxKnownTopics = g.getGroupKnowledge().cardinality();
				}	
			}

			maxKnownTopicsAndGroups[0] = maxKnownTopics;

			for (Group p : myGroups) {
				if (p.getGroupKnowledge().cardinality() == maxKnownTopics) {
					maxKnownTopicsAndGroups[1]++;
				}
			}

			return maxKnownTopicsAndGroups;
		}
	}
}

