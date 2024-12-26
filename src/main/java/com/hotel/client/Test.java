package com.hotel.client;

import java.util.*;
import com.hotel.client.AdminOperation.AccommodationOperation;
import com.hotel.client.AdminOperation.AmminitiesOperatin;
import com.hotel.client.entity.*;
import com.hotel.client.service.*;

public class Test {
    static LoginEntity le = new LoginEntity();
    static StateEntity se = new StateEntity();
    static DistrictServiceImpl districtService = new DistrictServiceImpl();
    static HotelEntity he = new HotelEntity();
    static IHotelService hs = new HotelServiceImpl();
    static CityEntity ce = new CityEntity();
    static ICityService cs = new CityServiceImple();
    static IAccommodationService as = new AccommodationServiceImple();
    static IAmminitiesService ams = new AmminitiesServiceImple();
    static ILoginService iLoginService = new LoginServiceImpl();
    static IStateServices iStateServices = new StateServices();
    static IUserService us = new UserSeviceImpl();
    static Scanner sc = new Scanner(System.in);
    static RecomendationEntity re = new RecomendationEntity();

    public static void main(String[] args) {
        List<StateEntity> states = iStateServices.getAllStates();
        displayStates(states);

        System.out.println("Enter the state name:");
        String stateName = sc.nextLine();
        int stateId = iStateServices.getSatteIdByName(stateName);

        if (stateId != 0) {
            handleDistricts(stateId);
        } else {
            System.err.println("You entered an incorrect state name.");
        }
    }

    private static void displayStates(List<StateEntity> states) {
        System.out.println("***************** STATES ********************");
        System.out.println("State_id\t State_Name");
        states.forEach(state -> System.out.println(state.getS_id() + "\t\t" + state.getS_name()));
    }

    private static void handleDistricts(int stateId) {
        List<DistrictEntity> districts = districtService.showAllDistrcitWhitState(stateId);

        if (!districts.isEmpty()) {
            System.out.println("***************** Districts ********************");
            System.out.println("District_id\t District_Name");
            districts.forEach(d -> System.out.println(d.getDistId() + "\t\t" + d.getDistName()));

            System.out.println("Enter the District name:");
            String districtName = sc.nextLine();
            int districtId = districtService.getDistIdByName(districtName);

            if (districtId != 0) {
                handleCities(stateId, districtId);
            } else {
                System.out.println("District not found.");
            }
        } else {
            System.out.println("No districts found for the selected state.");
        }
    }

    private static void handleCities(int stateId, int districtId) {
        ce.setS_id(stateId);
        ce.setDistId(districtId);
        List<CityEntity> cities = cs.showAllCities(ce);

        if (!cities.isEmpty()) {
            System.out.println("*************** Cities ****************");
            System.out.println("city_id \t city_Name");
            cities.forEach(city -> System.out.println(city.getCityId() + "\t\t" + city.getCityName()));

            System.out.println("Enter city name to find hotels:");
            String cityName = sc.nextLine();
            ce.setCityName(cityName);
            int cityId = cs.getCityIdByName(ce);

            if (cityId != 0) {
                ce.setCityId(cityId);
                int locationId = hs.getLocationId(ce);
                handleAccommodations(locationId);
            } else {
                System.out.println("City not found.");
            }
        } else {
            System.out.println("No cities found for the selected district.");
        }
    }

    private static void handleAccommodations(int locationId) {
        AccommodationOperation.showAccommodation();
        System.out.println("Enter the Id of the accommodation type:");
        int accommodationId = sc.nextInt();
        sc.nextLine();

        if (as.check(accommodationId)) {
            System.out.println("Enter the minimum price range:");
            int minPrice = sc.nextInt();
            System.out.println("Enter the maximum price range:");
            int maxPrice = sc.nextInt();
            sc.nextLine();

            List<RecomendationEntity> hotels = hs.showAllHotelWithAcc(locationId, accommodationId, minPrice, maxPrice);
            displayHotels(hotels);

            if (!hotels.isEmpty()) {
                handleAminities(hotels);
            } else {
                System.out.println("No hotels found in the specified price range.");
            }
        } else {
            System.out.println("Invalid accommodation type ID.");
        }
    }

    private static void displayHotels(List<RecomendationEntity> hotels) {
        System.out.println("****************************** Hotels *****************************************");
        hotels.forEach(hotel -> System.out.println(hotel.getH_id() + "\t" + hotel.getH_name() + "\t"
                + hotel.getH_add() + "\t" + hotel.getH_type() + "\t Total price = " + hotel.getPrice()
                + "\nAmenities = " + hotel.getAminity()
                + "\n----------------------------------------------------------------------------------------"));
    }

    private static void handleAminities(List<RecomendationEntity> hotels) {
        System.out.println("Do you want to filter by amenities? (yes/no):");
        String response = sc.nextLine().toLowerCase();

        List<String> userAmenities = new ArrayList<>();
        if (response.equals("yes")) {
            AmminitiesOperatin.showAmminities();
            while (true) {
                System.out.println("Enter amenity name:");
                userAmenities.add(sc.nextLine());
                System.out.println("Do you want to add more amenities? (yes/no):");
                if (!sc.nextLine().toLowerCase().equals("yes")) break;
            }
        }

        String selectedAmenities = String.join(",", userAmenities);
        RecomendationEntity userPreference = new RecomendationEntity();
        userPreference.setAminity(selectedAmenities);

        List<RecomendationEntity> recommendations = recommendHotels(userPreference, hotels);
        displayHotels(recommendations);
    }

    private static double calculateCosineSimilarity(double[] vec1, double[] vec2) {
        double dotProduct = 0.0, normVec1 = 0.0, normVec2 = 0.0;
        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            normVec1 += Math.pow(vec1[i], 2);
            normVec2 += Math.pow(vec2[i], 2);
        }
        return (normVec1 == 0 || normVec2 == 0) ? 0 : dotProduct / (Math.sqrt(normVec1) * Math.sqrt(normVec2));
    }

    private static double[] extractFeatures(RecomendationEntity hotel) {
        return new double[] {
            hotel.hasAmenity("wifi") ? 1.0 : 0.0,
            hotel.hasAmenity("pool") ? 1.0 : 0.0,
            hotel.hasAmenity("gym") ? 1.0 : 0.0
        };
    }

    public static List<RecomendationEntity> recommendHotels(RecomendationEntity userPreference, List<RecomendationEntity> hotels) {
        double[] userFeatures = extractFeatures(userPreference);
        Map<RecomendationEntity, Double> similarityScores = new HashMap<>();

        for (RecomendationEntity hotel : hotels) {
            double similarity = calculateCosineSimilarity(userFeatures, extractFeatures(hotel));
            similarityScores.put(hotel, similarity);
        }

        return similarityScores.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
}
