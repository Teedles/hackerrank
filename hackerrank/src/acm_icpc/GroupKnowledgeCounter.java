/**
 * my solution to the HR acm icpc challenge
 * note, this is a little different to the actual requirements:
 * for example, I generate knowledge of topics randomly and I don't read the number of people and topics from stdin
 */

package acm_icpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @author Teedles
 *
 */
public class GroupKnowledgeCounter {

	public final static int KNOWLEDGE_BANDWITH = 500;
	public final static int PERSON_BANDWITH = 500;
	
	/**
	 * 
	 */
	public GroupKnowledgeCounter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//SETUP
		int maxGroups = returnMaxGroups(PERSON_BANDWITH);
		Person[] people = new Person[PERSON_BANDWITH];
		long startTime;
		long stopTime;
		
		for (int i=0;i<people.length; i++) {
			people[i] = new Person();
			people[i].setPersonID(i);
		}
		
		List<Group> groups = generateGroups(people);
	
		// COMPUTATION
		startTime = System.currentTimeMillis();
		int[] maxKnownTopics = countKnownTopicsAndGroups(groups);
		stopTime = System.currentTimeMillis(); 
		
		// PRESENTATION
		System.out.println("Max possible groups is: " + maxGroups);
		System.out.println("Max known topics is: " + maxKnownTopics[0]);
		System.out.println("Number of Groups that know the max Topics is: " + maxKnownTopics[1]);
		System.out.println("Computation took: "+ (stopTime - startTime) + " millis");
	}
	
	/**
	 *  for all our groups, determine the max topics known
	 *  also count the amount of groups that know that number of topics
	 * @param groups
	 * @return an int array with the numbers counted
	 */
	private static int[] countKnownTopicsAndGroups(List<Group> groups) {
		
		int[] maxKnownTopicsAndGroups = new int[] {0,0};
		int maxKnownTopics = 0; 

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
	private static List<Group> generateGroups(Person[] people) {

		List<Group> tempGroups = new ArrayList<Group>();
		List<Person> removePersons = new ArrayList<Person>();

		for (Person p : people) {

			removePersons.add(p);
			List<Person> members = new ArrayList<Person>(Arrays.asList(people));

			for (Person r : removePersons) {
				members.remove(r);
			}

			for (Person q : members) {
				Group g = new Group(p,q);
				tempGroups.add(g);
			}
		}	

		return tempGroups;
	}

	/**
	 * make a bunch of people according to our configured bandwidth
	 * @return an array of people
	 */
	public Person[] generatePeople() {
		Person[] tempPeople = new Person[PERSON_BANDWITH];
		
		for (int i=0; i<PERSON_BANDWITH; i++) {
			tempPeople[i] = new Person();
			tempPeople[i].setPersonID(i);
		}
		
		return tempPeople;
	}
	
	/**
	 * using BITSETs, whooo
	 * @return a BitSet of random bits
	 */
	public static BitSet generateKnowledge() {
		BitSet tempKnowledge = new BitSet(KNOWLEDGE_BANDWITH);
		Random rand = new Random();
		
		for (int i=0; i<KNOWLEDGE_BANDWITH; i++) {
			if (rand.nextBoolean() == true) tempKnowledge.set(i);
		}
		
		return tempKnowledge;
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
		
		private int personID;
		private BitSet personKnowledge;
		
		/**
		 * @return the personID
		 */
		public int getPersonID() {
			return personID;
		}

		/**
		 * @param personID the personID to set
		 */
		public void setPersonID(int personID) {
			this.personID = personID;
		}

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
		
		public Person() {
			this.setPersonKnowledge(generateKnowledge());
		}
	}

	/**
	 * group class. also overkill really
	 * note: on instantiation, the OR of the two persons knowledge is generated
	 *
	 */
	public static class Group {
		/**
		 * @return the p1
		 */
		public Person getP1() {
			return p1;
		}

		/**
		 * @param p1 the p1 to set
		 */
		public void setP1(Person p1) {
			this.p1 = p1;
		}

		/**
		 * @return the p2
		 */
		public Person getP2() {
			return p2;
		}

		/**
		 * @param p2 the p2 to set
		 */
		public void setP2(Person p2) {
			this.p2 = p2;
		}

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

		private Person p1;
		private Person p2;
		private BitSet groupKnowledge;

		public Group(Person p1, Person p2) {
			this.setP1(p1);
			this.setP2(p2);
			
			this.getP1().getPersonKnowledge().or(this.getP2().getPersonKnowledge());
			
			this.setGroupKnowledge((BitSet) this.getP1().getPersonKnowledge().clone() );
		}
	}
}

