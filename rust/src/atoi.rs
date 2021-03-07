use std::collections::HashSet;
use std::str::FromStr;
use std::num::ParseIntError;

pub fn my_atoi(s: String) -> i32 {
    let mut sign = '+';
    let mut index = 0;
    let nums = nums_hash();
    let mut num_str = String::new();
    let mut valid = true;
    enum Next {
        Digit,
        Stop,
        Anything
    }
    let mut next = Next::Anything;
    for (i, c) in s.chars().enumerate() {
        match next {
            Next::Digit => {
                if nums.contains(&c) {
                    num_str.insert(index, c);
                    index+=1;
                } else {
                    next = Next::Stop;
                }
            }
            Next::Stop => {
                break;
            }
            Next::Anything => {
                if c == ' ' {
                    continue;
                }
                if c == '+' || c == '-' {
                    next = Next::Digit;
                    sign = c;
                    continue;
                }
                if !nums.contains(&c) {
                    next = Next::Stop;
                } else {
                    next = Next::Digit;
                    num_str.insert(index, c);
                    index+=1;
                }
            }
        };
    }
    if num_str.is_empty() || !valid {
        return 0;
    }

    parse(num_str, sign)
}

pub fn nums_hash() -> HashSet<char> {
    let mut set = HashSet::new();
    set.insert('0');
    set.insert('1');
    set.insert('2');
    set.insert('3');
    set.insert('4');
    set.insert('5');
    set.insert('6');
    set.insert('7');
    set.insert('8');
    set.insert('9');
    set
}

pub fn parse(num: String, sign: char) -> i32 {
    if num.is_empty() {
        return 0;
    };

    let result = i32::from_str(num.as_str());
    return match result {
        Ok(n) => {
            if sign == '+' {
                return n;
            }
            n * -1
        }
        Err(err) => {
            if sign == '+' {
                return std::i32::MAX;
            }
            std::i32::MIN
        }
    }
}
#[cfg(test)]
mod tests {
    use crate::atoi::my_atoi;

    #[test]
    fn test() {
        assert_eq!(42, my_atoi("42".to_owned()));
        assert_eq!(4193, my_atoi("4193 with words".to_owned()));
        assert_eq!(0, my_atoi("words and 987".to_owned()));
        assert_eq!(-2147483648, my_atoi("-91283472332".to_owned()));
        assert_eq!(0, my_atoi("".to_owned()));
        assert_eq!(3, my_atoi("3.14159".to_owned()));
        assert_eq!(-12, my_atoi("  -0012a42".to_owned()));
        assert_eq!(-5, my_atoi("  -5-".to_owned()));
        assert_eq!(0, my_atoi("  +  413".to_owned()));
        assert_eq!(0, my_atoi("00000-42a1234".to_owned()));
    }
}