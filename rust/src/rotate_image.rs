use std::{mem, ptr};
use std::borrow::BorrowMut;

pub fn rotate(matrix: &mut Vec<Vec<i32>>) {
    if matrix.len() == 0 {
        return;
    }
    if matrix.get(0).unwrap().len() != matrix.len() {
        return;
    }

    for i in 0..matrix.len() {
        for j in i..matrix.len() {
            if i != j {
                let temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    for i in 0..matrix.len() {
        let s = matrix[i].as_mut_slice();
        let mut i = 0;
        let mut j = s.len()-1;
        while i < j {
            let temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i+=1;
            j-=1;
        }
    }
}


#[cfg(test)]
mod tests {
    use crate::rotate_image::{rotate};

    #[test]
    fn it_works() {
        rotate(&mut vec![vec![1,2,3],vec![4,5,6],vec![7,8,9]])
    }
}