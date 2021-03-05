pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut result = vec![];
    let mut index = 0;
    let size = nums.len();
    for i in 0 .. size - 1 {
        for j in i+1 .. size {
            let first = nums.get(i).unwrap();
            let second = nums.get(j).unwrap();
            if first + second == target {
                result.push(i as i32);
                result.push((j) as i32);
                return result;
            }
        }
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn two_sum_test() {
        let num = vec![2,7,11,15];
        let result = two_sum(num, 9);
        assert_eq!(vec![0,1], result);
        let num = vec![3,2,4];
        let result = two_sum(num, 6);
        assert_eq!(vec![1,2], result);
        let num = vec![3,2,3];
        let result = two_sum(num, 6);
        assert_eq!(vec![0,2], result);
    }
}