pub fn max_area(height: Vec<i32>) -> i32 {
    if height.len() == 0 || height.len() == 1{
        return 0;
    }
    let mut i = 0;
    let mut j = height.len()-1;
    let mut c = 0;
    let mut area = 0;
    let mut max_area = 0;
    loop {
         if c == height.len()-1{
            break;
        }
        let left_most = height.get(i).unwrap();
        let right_most = height.get(j).unwrap();
        area = left_most.min(right_most) * (j - i) as i32;
        if left_most <= right_most {
            i+=1;
        }
        if right_most <= left_most {
            j-=1;
        }
        max_area = area.max(max_area);
        c+=1;
    }
    max_area
}

mod tests {
    use crate::container_with_most_water::max_area;

    #[test]
    fn it_works() {
        assert_eq!(45, max_area(vec![3,9,3,4,7,2,12,6]));
        assert_eq!(1, max_area(vec![1,1]));
        assert_eq!(2, max_area(vec![1,2,1]));
        assert_eq!(16, max_area(vec![4,3,2,1,4]));
        assert_eq!(4, max_area(vec![1,2,4,3]));
        assert_eq!(24, max_area(vec![1,3,2,5,25,24,5]));
    }
}