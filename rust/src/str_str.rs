pub fn str_str(haystack: String, needle: String) -> i32 {
    if haystack.len() == 0 && needle.len() > 0 {
        return -1;
    }
    if haystack.len() == 0 && needle.len() == 0 {
        return 0;
    }
    if haystack.len() > 0 && needle.len() == 0 {
        return 0;
    }
    if needle.len() > haystack.len() {
        return -1;
    }

    let mut patt_arr: Vec<i32> = vec![];
    let mut j = 0;
    let mut i = 1;
    let mut c = 1;
    patt_arr.insert(0, 0);
    while c < needle.len() {
        let v1 = needle.get(j..j + 1).unwrap();
        let v2 = needle.get(i..i + 1).unwrap();
        if v1 == v2 {
            patt_arr.insert(c, (j + 1) as i32);
            i += 1;
            j += 1;
            c += 1;
        } else {
            if j == 0 {
                patt_arr.insert(c, 0);
                i += 1;
                c += 1;
            } else {
                j = *patt_arr.get(j - 1).unwrap() as usize;
            }
        }
    }

    let mut i = 0;
    let mut j = 0;

    let mut first_occ: i32 = -1;

    let mut start = false;
    while i < needle.len() && j < haystack.len() {
        let v1 = needle.get(i..i + 1).unwrap();
        let v2 = haystack.get(j..j + 1).unwrap();
        if v1 == v2 {
            if !start {
                first_occ = j as i32;
                start = true;
            }
            i += 1;
            j += 1;
        } else if i != 0 {
            i = *patt_arr.get(i - 1).unwrap() as usize;
            if start {
                first_occ = (j - i) as i32;
            }
            if i == 0 {
                start = false;
                first_occ = -1;
            } else {
                first_occ = (j - i) as i32;
            }
        } else {
            j += 1;
        }
        if i == patt_arr.len() {
            return first_occ;
        }
    }
    -1
}

#[cfg(test)]
mod tests {
    use crate::str_str::str_str;

    #[test]
    fn it_works() {
        assert_eq!(2, str_str("hello".to_owned(), "ll".to_owned()));
        assert_eq!(-1, str_str("aaaaa".to_owned(), "bba".to_owned()));
        assert_eq!(0, str_str("".to_owned(), "".to_owned()));
        assert_eq!(-1, str_str("aaa".to_owned(), "aaaa".to_owned()));
        assert_eq!(4, str_str("mississippi".to_owned(), "issip".to_owned()));
        assert_eq!(-1, str_str("mississippi".to_owned(), "issipi".to_owned()));
        assert_eq!(9, str_str("mississippi".to_owned(), "pi".to_owned()));
        assert_eq!(0, str_str("a".to_owned(), "a".to_owned()));
    }
}