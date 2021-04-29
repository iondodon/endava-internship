package com.endava.internship.service;

import com.endava.internship.domain.Privilege;
import com.endava.internship.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public interface UserService {

    /**
     * Should return a list of first names sorted in descending order
     *
     * @param users list of users to extract names from
     * @return reverse ordered list of first names
     */
    List<String> getFirstNamesReverseSorted(List<User> users);

    /**
     * Should sort given list of user by age descending
     * and first name ascending
     *
     * @param users list of users to sort
     * @return sorted list of users
     */
    List<User> sortByAgeDescAndNameAsc(List<User> users);


    /**
     * Should return all distinct privileges for given users
     *
     * @param users list of users
     * @return distinct privileges
     */
    List<Privilege> getAllDistinctPrivileges(List<User> users);


    /**
     * Should return optional of user that has UPDATE privilege
     * and age higher that given age
     *
     * @param users list of users
     * @param age   conditional age
     * @return optional of found user or optional empty if user wasn't found
     */
    Optional<User> getUpdateUserWithAgeHigherThan(List<User> users, int age);

    /**
     * Should return grouped map by number of user's privileges
     *
     * @param users list of users
     * @return grouped map
     */
    Map<Integer, List<User>> groupByCountOfPrivileges(List<User> users);


    /**
     * Calculates average age for given users
     *
     * @param users list of user
     * @return return average age for users or -1 if empty list is passed
     */
    double getAverageAgeForUsers(List<User> users);


    /**
     * Should calculate the most frequent last name from list of given users
     *
     * @param users list of users
     * @return optional of most frequent last name (if it encountered at least two times)
     * or optional empty if number of last names is the same or list of users is empty
     */
    Optional<String> getMostFrequentLastName(List<User> users);

    /**
     * Filters list of given users by given criteria.
     *
     * @param users      list of users to filter
     * @param predicates predicates for filtering
     * @return filtered list or the same list of users if not predicates were passed
     */
    List<User> filterBy(List<User> users, Predicate<User>... predicates);

    /**
     * Should convert list of users to comma separated values string separated by given delimiter,
     * values are calculated from given map function.
     *
     * @param users     list of users to convert
     * @param delimiter csv delimiter
     * @param mapFun    csv value extractor
     * @return csv string
     */
    String convertTo(List<User> users, String delimiter, Function<User, String> mapFun);

    /**
     * Should return grouped map by user privileges.
     *
     * @param users list of users to group
     * @return map of privileges and users that has that privilege
     */
    Map<Privilege, List<User>> groupByPrivileges(List<User> users);

    /**
     * Should return map of last names and encountered number
     *
     * @param users list of users
     * @return map of last names and encountered number
     */
    Map<String, Long> getNumberOfLastNames(final List<User> users);
}


