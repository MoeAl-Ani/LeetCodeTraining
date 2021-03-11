use std::collections::HashMap;

pub fn int_to_roman(num: i32) -> String {
    if num < 1 || num > 3999 {
        return "".to_owned();
    }
    let mut num = num;
    let mut num_digits = vec![];

    while num > 0 {
        num_digits.push(num % 10);
        num = num / 10;
    }
    let size = num_digits.len();
    let mut c = (size - 1) as i32;
    let mut result: String = String::new();
    loop {
        if c < 0 {
            break;
        }
        let current = num_digits.get(c as usize).unwrap();
        if *current == 0 {
            c-=1;
            continue;
        }
        let m = multiplier(&(c as i32));
        match c {
            0 => {
                let current = *current * m;
                if current == 5 {
                    result.push_str("V");
                } else if current == 4 {
                    result.push_str("IV");
                } else if current == 9 {
                    result.push_str("IX");
                } else if current < 4 {
                    let mut cc = current;
                    while cc > 0 {
                        result.push_str("I");
                        cc -= 1;
                    }
                } else {
                    let mut cc = current;
                    result.push_str("V");
                    cc -= 5;
                    while cc > 0 {
                        result.push_str("I");
                        cc -= 1;
                    }
                }
            }
            1 => {
                let current = *current * m;
                if current == 10 {
                    result.push_str("X");
                } else if current == 50 {
                    result.push_str("L");
                } else if current == 40 {
                    result.push_str("XL");
                } else if current >= 10 && current < 40 {
                    let mut cc = current;
                    while cc > 0 {
                        result.push_str("X");
                        cc -= 10;
                    }
                } else if current == 90 {
                    result.push_str("XC")
                } else {
                    let mut cc = current;
                    result.push_str("L");
                    cc -= 50;
                    while cc > 0 {
                        result.push_str("X");
                        cc -= 10;
                    }
                }
            }
            2 => {
                let current = *current * m;
                if current == 100 {
                    result.push_str("C");
                } else if current == 400 {
                    result.push_str("CD");
                } else if current == 500 {
                    result.push_str("D");
                } else if current == 900 {
                    result.push_str("CM")
                } else if current < 400 {
                    let mut cc = current;
                    while cc >= 100 {
                        result.push_str("C");
                        cc-=100;
                    }
                } else {
                    let mut cc = current;
                    result.push_str("D");
                    cc -= 500;
                    while cc > 0 {
                        result.push_str("C");
                        cc -= 100;
                    }
                }
            }
            3 => {
                let current = *current * m;
                let mut cc = current;
                while cc > 0 {
                    result.push_str("M");
                    cc -= 1000;
                }
            }
            _ => {
                panic!("not expected")
            }
        }
        c -= 1;
    }
    result
}

pub fn multiplier(num: &i32) -> i32 {
    return match num {
        0 => 1,
        1 => 10,
        2 => 100,
        3 => 1000,
        _ => {
            panic!("not expected")
        }
    };
}

#[cfg(test)]
mod tests {
    use crate::integer_to_roman::int_to_roman;

    #[test]
    fn it_works() {
        assert_eq!("III", int_to_roman(3));
        assert_eq!("IV", int_to_roman(4));
        assert_eq!("IX", int_to_roman(9));
        assert_eq!("LVIII", int_to_roman(58));
        assert_eq!("MCMXCIV", int_to_roman(1994));
        assert_eq!("M", int_to_roman(1000));
        assert_eq!("LX", int_to_roman(60));
        assert_eq!("DC", int_to_roman(600));
    }
}