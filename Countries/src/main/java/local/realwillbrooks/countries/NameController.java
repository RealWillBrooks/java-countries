package local.realwillbrooks.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController {


    @RequestMapping(value = "/all",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    @RequestMapping(value = "/start/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> givenLetterCountry(
            @PathVariable
                    char letter) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @RequestMapping(value = "/size/{number}",
            produces = {"application/json"})
    public ResponseEntity<?> givenLengthCountry(
            @PathVariable
                    int number) {
        ArrayList<Country> rtnCounties = CountriesApplication.ourCountryList.
                findCountries(c -> c.getName().length() >= number);
        return new ResponseEntity<>(rtnCounties, HttpStatus.OK);
    }
}