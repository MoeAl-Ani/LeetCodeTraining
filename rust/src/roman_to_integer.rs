use std::collections::{HashMap, HashSet};
use std::iter::FromIterator;
use std::str::FromStr;

pub fn roman_to_int(s: String) -> i32 {
let mut roman_map = HashMap::new();
let mut roman_map_constraints: HashMap<&str, HashSet<char>> = HashMap::new();
    if s.len() < 1 || s.len() > 15 {
        return 0;
    }
    roman_map.insert("I", 1);
    roman_map_constraints.insert("I", HashSet::from_iter("VX".chars()));
    roman_map.insert("V", 5);
    roman_map.insert("X", 10);
    roman_map_constraints.insert("X", HashSet::from_iter("LC".chars()));
    roman_map.insert("L", 50);
    roman_map.insert("C", 100);
    roman_map_constraints.insert("C", HashSet::from_iter("DM".chars()));
    roman_map.insert("D", 500);
    roman_map.insert("M", 1000);
    if s.len() == 1 {
        return roman_map.get(s.as_str()).copied().unwrap();
    }

    let size = s.len();
    let mut index: usize = 0;
    let mut sum = 0;
    loop {
        if index >= size {
            break;
        }
        let r1 = s.get(index..index+1).unwrap();
        let n1 = roman_map.get(r1).copied().unwrap();

        if index == size - 1 {
            sum+=n1;
            break;
        }
        let r2 = s.get(index+1..index+2).unwrap();
        let n2 = roman_map.get(r2).copied().unwrap();
        if n1 < n2 {
            if  let Some(n11) = roman_map_constraints.get(r1) {
                if n11.contains(&char::from_str(r2).unwrap()) {
                    // minus and increase count by 2
                    sum+=n2-n1;
                    increment(&mut index,2);
                } else {
                    // add up to sum and increase count by 1
                    sum+=n1;
                    increment(&mut index,1);
                }
            } else {
                // add up to sum and increase count by 1
                sum+=n1;
                increment(&mut index,1);
            }
        } else {
            // add up to sum and increase count by 1
            sum+=n1;
            increment(&mut index,1);
        }
    }
    if sum > 3999 {
        return 0;
    }
    sum
}
pub fn increment(count: &mut usize, step: usize) {
    *count+=step
}

#[cfg(test)]
mod tests {
    use crate::roman_to_integer::roman_to_int;

    #[test]
    fn it_works() {
        assert_eq!(2, roman_to_int(format!("II")));
        assert_eq!(4, roman_to_int(format!("IV")));
        assert_eq!(9, roman_to_int(format!("IX")));
        assert_eq!(58, roman_to_int(format!("LVIII")));
        assert_eq!(1994, roman_to_int(format!("MCMXCIV")));
    }
}