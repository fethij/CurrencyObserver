//
//  CryptocurrencyCell.h
//  Currency Observer
//
//  Created by Admin on 17/04/2018.
//  Copyright © 2018 devjn. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CryptocurrencyCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIImageView *iconImage;
@property (weak, nonatomic) IBOutlet UITextView *titleText;
@property (weak, nonatomic) IBOutlet UITextView *priceText;

@end
