use std::collections::HashSet;

pub fn length_of_longest_substring(s: String) -> i32 {
    if s.is_empty() {return 0; }
    if s.len() == 1 { return 1; }
    let mut max = 0;
    let mut char_set:HashSet<&char> = HashSet::new();
    let mut start_index = 0;
    let mut c = 0;
    let mut s_vec = Vec::with_capacity(s.len());
    s.chars().for_each(|c| {s_vec.push(c)});
    loop {
        if c == s_vec.len() {
            break;
        }
        let s_char = s_vec.get(c).unwrap();
        if char_set.contains(s_char) {
            max = max.max(char_set.len() as i32);
            char_set.clear();
            start_index+=1;
            c = start_index;
        } else {
            char_set.insert(s_char);
            c+=1;
        }
    }
    max.max(char_set.len() as i32)
}


#[cfg(test)]
mod tests {
    use crate::longest_substring_without_repeating::length_of_longest_substring;

    #[test]
    fn test() {
        let input1 = format!("abcabcbb");
        let input2 = format!("bbbbb");
        let input3 = format!("pwwkew");
        let input4 = format!("");
        let input5 = format!("au");
        let input6 = format!("dvdf");
        assert_eq!(3, length_of_longest_substring(input1));
        assert_eq!(1, length_of_longest_substring(input2));
        assert_eq!(3, length_of_longest_substring(input3));
        assert_eq!(0, length_of_longest_substring(input4));
        assert_eq!(2, length_of_longest_substring(input5));
        assert_eq!(3, length_of_longest_substring(input6));
    }
}