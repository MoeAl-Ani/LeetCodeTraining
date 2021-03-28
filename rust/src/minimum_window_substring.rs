use std::collections::{HashMap};
use std::hash::Hash;

pub enum Next {
    Left,
    Right
}
pub fn is_valid_window(map: &mut HashMap<char, i32>, t_hash: &HashMap<char, i32>) -> bool {
    let mut init = true;
    let mut result = false;
    for (k,v) in t_hash {
        if !map.contains_key(k) {
            result = false;
            break;
        } else {
            if init {
                result = map.get(k).unwrap() >= v;
                init = false;
            } else {
                result = result && (map.get(k).unwrap() >= v);
            }
        }
    }
    result
}
pub fn min_window(s: String, t: String) -> String {
    let mut t_hash = HashMap::new();
    t.chars().for_each(|c| {
        if !t_hash.contains_key(&c) {
            t_hash.insert(c.clone(), 1);
        } else {
            t_hash.insert(c.clone(), t_hash.get(&c).unwrap() + 1);
        }
    });
    let mut tt_hash = HashMap::new();
    let mut curser = Next::Right;
    let mut left = 0;
    let mut right = 0;
    let s = s.chars().collect::<Vec<char>>();
    let mut minimum_window:String = format!("");
    while right < s.len() {
        match curser {
            Next::Left => {
                // smaller window found
                let valid_window = is_valid_window(&mut tt_hash, &t_hash);
                //let valid_window = tt_hash.values().all(|c| *c >= 1 && (tt_hash.len() == t.len()));
                if valid_window {
                    let size = (right - left) + 1;
                    if size < minimum_window.len() {
                        minimum_window = s[left..right + 1].iter().collect::<String>();
                    }
                    let char = s.get(left).unwrap();
                    if tt_hash.contains_key(char) {
                        tt_hash.insert(char.clone(), *tt_hash.get(char).unwrap() - 1);
                    }
                    left+=1;
                } else {
                    right+=1;
                    curser = Next::Right;
                }
            }
            Next::Right => {
                let char = s.get(right).unwrap();
                if t_hash.contains_key(char) {
                    if !tt_hash.contains_key(char) {
                        tt_hash.insert(char.clone(), 1);
                    } else {
                        tt_hash.insert(char.clone(), tt_hash.get(char).unwrap() + 1);
                    }
                }
                let valid_window = is_valid_window(&mut tt_hash, &t_hash);
                //let valid_window = tt_hash.values().all(|c| *c >= 1 && tt_hash.len() == t.len());
                if valid_window {
                    let size = (right - left) + 1;
                    let l_char = s.get(left).unwrap();
                    if tt_hash.contains_key(l_char) {
                        tt_hash.insert(l_char.clone(), *tt_hash.get(l_char).unwrap() - 1);
                    }
                    if minimum_window.len() == 0 {
                        minimum_window = s[left..right + 1].iter().collect::<String>();
                    } else if size < minimum_window.len() {
                        minimum_window = s[left..right + 1].iter().collect::<String>();
                    }
                    left+=1;
                    curser = Next::Left;
                } else {
                    right+=1;
                }
            }
        }
    }

    minimum_window
}

#[cfg(test)]
mod tests {
    use crate::minimum_window_substring::min_window;

    #[test]
    fn it_works() {
        assert_eq!("BANC", min_window("ADOBECODEBANC".to_owned(), "ABC".to_owned()));
        assert_eq!("a", min_window("a".to_owned(), "a".to_owned()));
        assert_eq!("aa", min_window("aa".to_owned(), "aa".to_owned()));
        assert_eq!("", min_window("a".to_owned(), "aa".to_owned()));
    }
}